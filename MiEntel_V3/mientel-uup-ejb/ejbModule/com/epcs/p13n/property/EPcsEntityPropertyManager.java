/* Propiedad de Entel PCS. Todos los derechos reservados */
package com.epcs.p13n.property;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import weblogic.ejb.GenericSessionBean;
import weblogic.ejbgen.Constants;
import weblogic.ejbgen.FileGeneration;
import weblogic.ejbgen.JndiName;
import weblogic.ejbgen.RemoteMethod;
import weblogic.ejbgen.Session;

import com.bea.p13n.cache.Cache;
import com.bea.p13n.cache.CacheFactory;
import com.bea.p13n.property.ConfigurableEntityCreateException;
import com.bea.p13n.property.EntityNotFoundException;
import com.bea.p13n.property.EntityPropertyCache;
import com.bea.p13n.property.PropertyLocator;
import com.bea.p13n.property.PropertyMapKey;
import com.bea.p13n.property.PropertyValidationException;
import com.bea.p13n.property.internal.EntityPropertyCacheImpl;
import com.bea.p13n.property.internal.PropertyMapKeyImpl;
import com.epcs.cliente.perfil.dao.PerfilDAO;
import com.epcs.recursoti.exception.DAOException;
import com.epcs.recursoti.exception.ServiceException;
import com.epcs.seguridad.bean.PerfilUsuarioBean;

/**
 * Esta clase implementa los metodos necesarios para unificar las propiedades de perfiles de un usuario
 * determinado a traves de los metodos que provee la interface EntityPropertyManager.
 * Este EJB esta registrado con el administrador de perfiles del framework de Weblogic en el EJB deployment descriptor. 
 * 
 * Este EntityPropertyManager gestiona las propiedades de usuarios con base en un servicio web externo consultado por medio del numero del movil.
 */
@Session(ejbName = "EPcsEntityPropertyManager")
@JndiName(remote = "ejb.EPcsEntityPropertyManagerRemoteHome")
@FileGeneration(remoteClass = Constants.Bool.TRUE, remoteHome = Constants.Bool.TRUE, localClass = Constants.Bool.FALSE, localHome = Constants.Bool.FALSE)
public class EPcsEntityPropertyManager extends GenericSessionBean implements
SessionBean {
    
    /**
     * Logger de la aplicacion.
     */
    private Logger log = Logger.getLogger(EPcsEntityPropertyManager.class);


    /**
     * Serial Id.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Clase que encapsula la conexion con el servicio.
     */
    private PerfilDAO perfilDAO = null;

    /**
     * Nombre de la cache usada para obtener las propiedades en el entorno de aplicacion. Esta cache se configura y administra
     * a traves de la  consola de administracion del portal.
     */
    protected static final String PROPERTY_CACHE_NAME = "EPCSUUPCache";
    
    protected static final String DEFAULT_PROPERTY_SET = "mientel";

    protected static final boolean USE_CACHE = true;

    /**
     * Separador para pkString
     */
    private static final String DEFAULT_SEPARATOR_CHAR = "\\|";
    
    /**
     * Profile propery names Must match the names used in the property set
     * definition (.usr)
     */
    protected static final String PCS_NUMBER_PROPERTY = "numeroPcs";
    protected static final String USER_RUT_PROPERTY = "rutUsuario";
    protected static final String USER_NAME_PROPERTY = "nombreUsuario";
    protected static final String PLAN_TYPE_PROPERTY = "mercado";
    protected static final String IS_BAM_PROPERTY = "flagBam";
    protected static final String AAA_PROPERTY = "aaa";
    protected static final String OWNER_RUT_PROPERTY = "rutTitular";
    protected static final String OWNER_NAME_PROPERTY = "nombreTitular";
    protected static final String ACCOUNT_NUMBER_PROPERTY = "numeroCuenta";
    protected static final String CURRENT_PCS_NUMBER_PROPERTY = "numeroPcsSeleccionado";
    protected static final String CURRENT_RUT_PROPERTY = "rutUsuarioSeleccionado";
    protected static final String CURRENT_NAME_PROPERTY = "nombreUsuarioSeleccionado";
    protected static final String CURRENT_SUBMERCADO_PROPERTY = "subMercadoSeleccionado";
    protected static final String USER_SUBMERCADO_PROPERTY = "subMercado";
    protected static final String USER_GRUPO_CLIENTE_PROPERTY = "grupoCliente";
    protected static final String USER_CATEGORIA_CLIENTE_PROPERTY = "categoriaCliente";
    
    /**
     * Metodo para crear entidades. no se usa.
     */
    public void ejbCreate() throws CreateException {
    }

       /**
        * Constructor que inicializa el DAO.
        */
    public EPcsEntityPropertyManager() {
        perfilDAO = new PerfilDAO();
    }

    /**
     * Este metodo devuelve un EntityPropertyCache que contiene todas las propiedades asociadas a un movil dado como parametro en 
     * el atributo PropertyLocator.
     * 
     * @param locator un objeto PropertyLocator que identifica la entidad del usuario.
     * @return un objeto EntityPropertyCache que encapsula las propiedades del usuario.
     * @exception EntityNotFoundException si el usuario definido en el PropertyLocator no existe.
     */
    @RemoteMethod()
    public EntityPropertyCache getProperties(final PropertyLocator locator)
            throws EntityNotFoundException {

        log.debug(locator.getPkString()  + " - user Property cache requested");
        EntityPropertyCache propertyCache = null;
        propertyCache = getItemFromCache(locator);
        
        if (propertyCache == null) {
            log.debug(locator.getPkString()  + " - user not in cache");
            //la cache no contiene las propieades por lo tanto se obtendran directamente desde el servicio a traves del DAO.
            propertyCache = new EntityPropertyCacheImpl();
//            PerfilUsuarioBean uupBean = perfilDAO.getUUPProperties(locator
//                    .getPkString());

            log.debug(locator.getPkString() + " obtaining user profile");
            PerfilUsuarioBean uupBean = null;
            try {
                String[] splitted = locator.getPkString().split(DEFAULT_SEPARATOR_CHAR);
                
                log.debug("numero : " + splitted[0]);
                log.debug("token : " + splitted[1]);
                
                uupBean = perfilDAO.obtenerPerfilUsuario(splitted[0]);
            } catch (DAOException e) {
                log.error("DAOException caught " + e.getMessage());
                throw new EntityNotFoundException(e);
            } catch (ServiceException e) {
                log.error("ServiceException caught " + e.getCodigoRespuesta()
                        + " - " + e.getDescripcionRespuesta());
                throw new EntityNotFoundException(e);
            } catch (Exception e) {
                log.error("Exception caught " + e.getMessage());
                throw new EntityNotFoundException(e);
            }
            
            log.info("UUP -> numeroPcs: "+uupBean.getNumeroPcs());
            if (uupBean.getRutUsuario()!=null) {
                log.info("UUP -> rutUsuario: "+uupBean.getRutUsuario().toString());
            }
            log.info("UUP -> nombreUsuario: "+uupBean.getNombreUsuario());
            log.info("UUP -> mercado: "+uupBean.getMercado());
            log.info("UUP -> flagBam: "+uupBean.getFlagBam());
            log.info("UUP -> aaa: "+uupBean.getAaa());
            if (uupBean.getRutTitular()!=null) {
                log.info("UUP -> rutTitular: "+uupBean.getRutTitular().toString());
            }
            log.info("UUP -> nombreTitular: "+uupBean.getNombreTitular());
            log.info("UUP -> numeroCuenta: "+uupBean.getNumeroCuenta());
            if (uupBean.getUsuarioSeleccionado()!=null){
                log.info("UUP -> numeroPcsSeleccionado: "+uupBean.getUsuarioSeleccionado().getNumeroPcs());
                log.info("UUP -> nombreUsuarioSeleccionado: "+uupBean.getUsuarioSeleccionado().getNombreUsuario());
                log.info("UUP -> subMercadoSeleccionado: "+uupBean.getUsuarioSeleccionado().getSubMercado());
                if (uupBean.getUsuarioSeleccionado().getRut()!=null) {
                    log.info("UUP -> rutUsuarioSeleccionado: "+uupBean.getUsuarioSeleccionado().getRut().toString());
                }
            }
            log.info("UUP -> subMercado: "+uupBean.getSubMercado());
            log.info("UUP -> grupoCliente: "+uupBean.getGrupoCliente());
            log.info("UUP -> categoriaCliente: "+uupBean.getCategoriaCliente());

            // Cachea las propiedades en un objeto PropertyMapKey
            PropertyMapKey planTypeMapKey = new PropertyMapKeyImpl(null,
                    PLAN_TYPE_PROPERTY);
            propertyCache.put(planTypeMapKey, uupBean.getMercado());

            PropertyMapKey pcsNumberMapKey = new PropertyMapKeyImpl(null,
                    PCS_NUMBER_PROPERTY);
            propertyCache.put(pcsNumberMapKey, uupBean.getNumeroPcs());

            PropertyMapKey userRutMapKey = new PropertyMapKeyImpl(null,
                    USER_RUT_PROPERTY);
            if (uupBean.getRutUsuario()!=null){
                propertyCache.put(userRutMapKey, uupBean.getRutUsuario().toString());
            }

            PropertyMapKey userNameMapKey = new PropertyMapKeyImpl(null,
                    USER_NAME_PROPERTY);
            propertyCache.put(userNameMapKey, uupBean.getNombreUsuario());

            PropertyMapKey flagBamMapKey = new PropertyMapKeyImpl(null,
                    IS_BAM_PROPERTY);
            propertyCache.put(flagBamMapKey, uupBean.getFlagBam());

            PropertyMapKey aaaMapKey = new PropertyMapKeyImpl(null, AAA_PROPERTY);
            propertyCache.put(aaaMapKey, uupBean.getAaa());

            PropertyMapKey ownerRutMapKey = new PropertyMapKeyImpl(null,
                    OWNER_RUT_PROPERTY);
            
            if (uupBean.getRutTitular()!=null) {
                propertyCache.put(ownerRutMapKey, uupBean.getRutTitular().toString());
            }
            
            PropertyMapKey ownerNameMapKey = new PropertyMapKeyImpl(null,
                    OWNER_NAME_PROPERTY);
            propertyCache.put(ownerNameMapKey, uupBean.getNombreTitular());
            
            
            PropertyMapKey accountNumberMapKey = new PropertyMapKeyImpl(null,
                    ACCOUNT_NUMBER_PROPERTY);
            propertyCache.put(accountNumberMapKey, uupBean.getNumeroCuenta());
            
            PropertyMapKey currentRutMapKey = new PropertyMapKeyImpl(null,
                    CURRENT_RUT_PROPERTY);
            
            if (uupBean.getUsuarioSeleccionado()!=null && uupBean.getUsuarioSeleccionado().getRut()!=null) {
                propertyCache.put(currentRutMapKey, uupBean.getUsuarioSeleccionado().getRut().toString());
            }
            
            PropertyMapKey currenNameMapKey = new PropertyMapKeyImpl(null,
                    CURRENT_NAME_PROPERTY);
            if (uupBean.getUsuarioSeleccionado()!=null) {
            	propertyCache.put(currenNameMapKey, uupBean.getUsuarioSeleccionado().getNombreUsuario());
            }            
            
            PropertyMapKey currentPcsNumberMapKey = new PropertyMapKeyImpl(null,
                    CURRENT_PCS_NUMBER_PROPERTY);
            if (uupBean.getUsuarioSeleccionado()!=null) {
            	propertyCache.put(currentPcsNumberMapKey, uupBean.getUsuarioSeleccionado().getNumeroPcs());
            }
            
            PropertyMapKey subMercadoMapKey = new PropertyMapKeyImpl(null,
                    USER_SUBMERCADO_PROPERTY);
            propertyCache.put(subMercadoMapKey, uupBean.getSubMercado());
            
            PropertyMapKey grupoClienteMapKey = new PropertyMapKeyImpl(null,
                    USER_GRUPO_CLIENTE_PROPERTY);
            propertyCache.put(grupoClienteMapKey, uupBean.getGrupoCliente());
            
            PropertyMapKey categoriaClienteMapKey = new PropertyMapKeyImpl(null,
                    USER_CATEGORIA_CLIENTE_PROPERTY);
            propertyCache.put(categoriaClienteMapKey, uupBean.getCategoriaCliente());            
            
            PropertyMapKey currentSubMercadoMapKey = new PropertyMapKeyImpl(null,
                    CURRENT_SUBMERCADO_PROPERTY);
            if (uupBean.getUsuarioSeleccionado()!=null) {
                propertyCache.put(currentSubMercadoMapKey, uupBean.getUsuarioSeleccionado().getSubMercado());
            }

            /*
             * Put the whole entity value cache into the application-wide cache.
             */
            log.debug("user entity value putted into cache");
            addItemToCache(locator, propertyCache);
        }
        log.debug(locator.getPkString()  + " - user Property cache obtained");
        return propertyCache;
    }

    /**
     * Este metodo devuelve el valor de una propiedad definida para el nombre y conjunto de propiedades definidas como parametro.
     * Si la propiedad no tiene un valor asociado, se retornara un valor nulo.
     * 
     * @param locator
     *            un objeto PropertyLocator que identifica al usuario del que se buscan las propiedades.
     * @param propertySet
     *            el nombre del conjunto de propiedades
     * @param propertyName
     *            el nombre de la propiedad
     * @return el valor de la propiedad o nulo si no se encuentra.
     * @exception EntityNotFoundException si la entidad que representa al usuario no existe.
     */
    @RemoteMethod()
    public Object getProperty(final PropertyLocator locator, final String propertySetName,
            final String propertyName) throws EntityNotFoundException {
        /*
         * Use getProperties() to check cache, get from database if not in
         * cache. The EntityPropertyCache is the container used to keep all user
         * properties in the application-wide cache.
         */
        log.debug(locator.getPkString() + " - user property requested: " + propertyName);
        EntityPropertyCache epc = getProperties(locator);
        PropertyMapKey propertyMapKey = new PropertyMapKeyImpl(null,
                propertyName);
        Object propertyValue = epc.get(propertyMapKey);
        log.debug(locator.getPkString() + " - user property value: " + propertyValue.toString());
        return epc.get(propertyMapKey);
    }

    /**
     * Remueve todos los valores de las propiedades asociadas con la entidad de usuario especificada
     * por identificador de propiedades. Despues dd que un valor es removido, las siguientes llamadas a getProperty() deben retornar null.
     * 
     * @param locator
     *            PropertyLocator identificador de la entidad del usuario.
     * @exception EntityNotFoundException
     *                si la entidad identificada por el atributo locator no existe
     */
    @RemoteMethod()
    public void removeProperties(final PropertyLocator locator)
            throws EntityNotFoundException {
        removeItemFromCache(locator);
        log.debug(locator.getPkString() + " - user properties removed");
    }

    /**
     * Remueve un valor de una propiedad especifica asociada a una entidad deusuario identificada por medio de una propiedad
     * llamada locator.
     * 
     * @param locator
     *            PropertyLocator identificador de la entidad del usuario.
     * @param propertySet
     *            el conjunto de propiedades qu contiene la propiedad a remover.
     * @param propertyName
     *            el nombre dela propiedad a remover
     * @return el valor que tenia la propiedad
     * @exception EntityNotFoundException
     *                si la entidad identificada por el atributo locator no existe
     */
    @RemoteMethod()
    public Object removeProperty(final PropertyLocator locator,
            final String propertySetName, final String propertyName)
            throws EntityNotFoundException {

        Object oldValue = getProperty(locator, propertySetName, propertyName);
        PropertyMapKey propertyMapKey = new PropertyMapKeyImpl(null,
                propertyName);
        removeCachedPropertyValue(locator, propertyMapKey);

        log.debug(locator.getPkString() + " - user property '" + propertyName
                + "' removed. Old value: " + oldValue);
        return oldValue;
    }

    /**
     * Sets the property identified by the given property set and property name
     * to the given value for the user entity specified by the given property
     * locator.
     * 
     * @param locator
     *            a PropertyLocator identifying the user entity to modify
     * @param propertySet
     *            the property set containing the property to modify
     * @param propertyName
     *            the name of the property to modify
     * @param value
     *            the value to persist for the given property and user entity
     * @exception PropertyValidationException
     *                if the value is not valid for the given property
     * @exception EntityNotFoundException
     *                if the entity identified by the property locator does not
     *                exist
     */
    @RemoteMethod()
    public void setProperty(PropertyLocator locator, String propertySet,
            String propertyName, Object propertyValue)
            throws PropertyValidationException, EntityNotFoundException {

        // Update the cache
        PropertyMapKey propertyMapKey = new PropertyMapKeyImpl(null,
                propertyName);
        cachePropertyValue(locator, propertyMapKey, propertyValue);
        
        log.debug(locator.getPkString() + " - user property '" + propertyName
                + "' setted to value: " + propertyValue.toString());

    }

    /**
     * Remove all properties, and the entity record, for the user entity
     * identified by the given property locator.
     * 
     * @param locator
     *            the PropertyLocator identifying the entity to remove
     * @exception EntityNotFoundException
     *                if the entity identified by the property locator does not
     *                exist
     */
    @RemoteMethod()
    public void removeEntity(PropertyLocator locator)
            throws EntityNotFoundException {
        // Remove it from the cache
        removeItemFromCache(locator);
        log.debug(locator.getPkString() + " - entity removed from cache");
    }

    /**
     * Create a record for a new user entity, as identified by the given
     * jndiHomeName (User or Group) and pkString. A custom EntityPropertyManager
     * is not used as the default EntityPropertyManager for the ProfileManager,
     * so you do not have to return a unique id for your user. Only the default
     * EntityPropertyManager is required to return a unique number. Simply
     * return -1.
     * 
     * @param jndiHomeName
     *            the entity's home name
     * @param pkString
     *            the entity's identifier string
     * @return the newly created unique user entity id
     * @exception ConfigurableEntityCreateException
     *                if there is an error
     */
    @RemoteMethod()
    public long createUniqueId(final String jndiHomeName, final String pkString)
            throws ConfigurableEntityCreateException {
        //Este metodo no es utilizado en el proyecto MiEntel v.3.0
        throw new UnsupportedOperationException();
    }

    /**
     * Only the default EntityPropertyManager has to support this method. A
     * custom EntityPropertyManager can simply throw
     * java.lang.UnsupportedOperationException
     */
    @RemoteMethod()
    public long getUniqueId(final String jndiHomeName, final String pkString)
            throws EntityNotFoundException {
      //Este metodo no es utilizado en el proyecto MiEntel v.3.0
        throw new UnsupportedOperationException();
    }

    /**
     * Only the default EntityPropertyManager has to support this method. A
     * custom EntityPropertyManager can simply throw
     * java.lang.UnsupportedOperationException
     */
    @RemoteMethod()
    public String[] getDynamicProperties(final PropertyLocator locator,
            String propertySet) throws EntityNotFoundException {
      //Este metodo no es utilizado en el proyecto MiEntel v.3.0
        throw new UnsupportedOperationException();
    }

    /**
     * Only the default EntityPropertyManager has to support this method. A
     * custom EntityPropertyManager can simply throw
     * java.lang.UnsupportedOperationException
     */
    @RemoteMethod()
    public String getHomeName(final long anEntityId) throws EntityNotFoundException {
      //Este metodo no es utilizado en el proyecto MiEntel v.3.0
      throw new UnsupportedOperationException();
    }

    /**
     * Only the default EntityPropertyManager has to support this method. A
     * custom EntityPropertyManager can simply throw
     * java.lang.UnsupportedOperationException
     */
    @RemoteMethod()
    public PropertyLocator getPropertyLocator(final long anEntityId)
            throws EntityNotFoundException {
      //Este metodo no es utilizado en el proyecto MiEntel v.3.0
        throw new UnsupportedOperationException();
    }

    /**
     * Only the default EntityPropertyManager has to support this method. A
     * custom EntityPropertyManager can simply throw
     * java.lang.UnsupportedOperationException
     */
    @RemoteMethod()
    public String[] getEntityNames(final String jndiName) throws RemoteException {
      //Este metodo no es utilizado en el proyecto MiEntel v.3.0
        throw new UnsupportedOperationException();
    }

    /**
     * Cache a property value for a user entity. The application-wide cache
     * contains a EntityPropertyCache, which is the container used to hold the
     * properties for the user entity. The application-wide cache contains
     * object references, so there is no need to update the application-wide
     * cache after changing the contents of the EntityPropertyCache container.
     * 
     * @param locator
     *            the entity's property locator
     * @param key
     *            the property's key
     * @param value
     *            the value to cache
     */
    private void cachePropertyValue(final PropertyLocator locator,
            PropertyMapKey key, Object value) {
        
        log.debug(locator.getPkString() + " - caching property '" + key.getPropertyName()
                + "' value: " + value.toString());
        
        if (!USE_CACHE) {
            log.debug(locator.getPkString() + " - no use for cache");
            return;
        }
        // Get any existing EntityPropertyCache container for the locator
        // (user).
        EntityPropertyCache cacheItem = getItemFromCache(locator);
        if (cacheItem != null) {
            // Add property to the cache
            cacheItem.put(key, value);
            log.debug(locator.getPkString() + " - property already cached, value updated");
        }
        else {
            // There was no EntityPropertyCache for this locator, so create one.
            cacheItem = new EntityPropertyCacheImpl();
            // Add property to the cache
            cacheItem.put(key, value);
            // Put the new EntityPropertyCache container into the cache
            addItemToCache(locator, cacheItem);
            log.debug(locator.getPkString() + " - property not cached, value added");

        }
    }

    /**
     * Remove a cached property value from the application-wide cache. The
     * application-wide cache contains a EntityPropertyCache, which is the
     * container used to hold the properties for the user entity. The
     * application-wide cache contains object references, so there is no need to
     * update the application-wide cache after changing the contents of the
     * EntityPropertyCache container.
     * 
     * @param locator
     *            the property locator of the user entity who owns the property
     * @param key
     *            the property's key
     */
    private void removeCachedPropertyValue(final PropertyLocator locator,
            PropertyMapKey key) {
        if (USE_CACHE) {
            EntityPropertyCache cacheItem = getItemFromCache(locator);
            if (cacheItem != null) {
                // Remove property from cache
                cacheItem.remove(key);
                log.debug(locator.getPkString()
                        + " - property value removed from cache. key "
                        + key.getPropertyName());
            }
        }
    }

    /**
     * Guarda en la cache un conjunto de propiedades que corresponden a una entidad de usuario.
     * Un EntityPropertyCache es un contenedor de propiedades de ambito local que usa un objeto PropertyLocator 
     * para identificar cada entidad de usuario relacionada.
     * 
     * @param locator
     *            la propiedad que identifica una entidad de usuario, usada como clave de la cache.
     * @param cacheItem
     *            el EntityPropertyCache que encapsula las propiedades el usuario
     */
    private void addItemToCache(final PropertyLocator locator,
            EntityPropertyCache cacheItem) {
        if (USE_CACHE) {
            Cache cache = CacheFactory.getCache(PROPERTY_CACHE_NAME);
            if (cache != null) {
//                cache.put(locator, cacheItem);
                cache.put(locator.getPkString(), cacheItem);
                log.debug(locator.getPkString()
                        + " - property added to cache. item: "
                        + cacheItem.toString());
            }
        }
    }

    /**
     * Obtiene el EntityPropertyCache que encapsula las propiedades de una entidad de usuario.
     * El EntityPropertyCache es un contenedor para los valores y no es una cache de nivel global. la cache de nivel global se llama
     * com.bea.p13n.cache.Cache
     * 
     * @param locator el objeto locator de la entidad del usuario
     * @return el objeto EntityPropertyCache, o null is este no se encuentra en la cache.
     */
    private EntityPropertyCache getItemFromCache(final PropertyLocator locator) {
        EntityPropertyCache cacheItem = null;
        if (USE_CACHE) {
            Cache cache = CacheFactory.getCache(PROPERTY_CACHE_NAME);
            if (cache != null) {
//                cacheItem = (EntityPropertyCache) cache.get(locator);
                cacheItem = (EntityPropertyCache) cache.get(locator.getPkString());
            }
        }
        return cacheItem;
    }

    /**
     * Remueve todas las propiedades de la cache asociadas a una entidad de usuario.
     * 
     * @param locator la entidad del locator.
     */
    private void removeItemFromCache(final PropertyLocator locator) {
        if (USE_CACHE) {
            Cache cache = CacheFactory.getCache(PROPERTY_CACHE_NAME);
            if (cache != null) {
//                cache.remove(locator);
                cache.remove(locator.getPkString());
                log.debug(locator.getPkString()
                        + " - cache item removed from cache");
            }
        }        
    }

    /**
     * Este metodo devuelve el valor de una propiedad del entorno del EJB.
     * 
     * @throws javax.naming.NamingException
     *             - si una naming exception es encontrada
     */
    protected final Object getEnvironmentProperty(final String propertyName)
            throws NamingException {
        Object envProperty = null;
        InitialContext jndiContext = new InitialContext();
        String lookupName = ((new StringBuffer("java:comp/env/"))
                .append(propertyName)).toString();
        envProperty = jndiContext.lookup(lookupName);
        log.debug("envProperty: " + envProperty + " (" + propertyName + ")");
        return envProperty;
    }
}
