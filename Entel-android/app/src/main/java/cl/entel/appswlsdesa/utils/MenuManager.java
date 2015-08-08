package cl.entel.appswlsdesa.utils;

import java.util.ArrayList;
import java.util.List;

import cl.entel.appswlsdesa.R;

/**
 * Created by alex on 8/5/14.
 */
public class MenuManager {
    private static final String CC = "cc";
    private static final String SS = "ss";
    private static final String PP = "pp";

    public static final int MENU_LOGIN = 0;
    public static final int MENU_SS = 1;
    public static final int MENU_CC = 2;
    public static final int MENU_PP = 3;
    private static MenuManager ourInstance = new MenuManager();

    private List<Option> mOptions;
    private List<Option> mCurrentMenuOptions;
    private List<Integer> mMenuLogin;
    private List<Integer> mMenuSS;
    private List<Integer> mMenuCC;
    private List<Integer> mMenuPP;

    public static MenuManager getInstance() {
        return ourInstance;
    }

    private MenuManager() {
        setOptions();
        setMenus();
    }

    public int getMenuMode (String mode) {
        if (mode.equalsIgnoreCase(CC)) {
            return MENU_CC;
        } else if (mode.equalsIgnoreCase(SS)) {
            return MENU_SS;
        } else if (mode.equalsIgnoreCase(PP)) {
            return MENU_PP;
        } else {
            return  -1;
        }
    }

    public List<Option> getMenuOptions(int menu) {
        mCurrentMenuOptions = new ArrayList<Option>();

        for (Integer i : getMenu(menu)) {
            mCurrentMenuOptions.add(mOptions.get(i));
        }

        return mCurrentMenuOptions;
    }

    public Option getCurrentMenuOption(int position) {
        return mCurrentMenuOptions.get(position);
    }

    private List<Integer> getMenu(int menu) {
        setMenus();
        switch (menu) {
            case MENU_LOGIN:
                return mMenuLogin;

            case MENU_SS:
                return mMenuSS;

            case MENU_CC:
                return mMenuCC;

            case MENU_PP:
                return mMenuPP;

            default:
                return null;
        }
    }

    private void setMenus() {
        mMenuLogin = new ArrayList<Integer>();
        mMenuSS = new ArrayList<Integer>();
        mMenuCC = new ArrayList<Integer>();
        mMenuPP = new ArrayList<Integer>();

        mMenuLogin.add(0);
        mMenuLogin.add(8);
        mMenuLogin.add(6);
        mMenuLogin.add(10);
        mMenuLogin.add(7);
//        mMenuLogin.add(9);

        mMenuSS.add(1);

        if (DataManager.getInstance().getUser() != null &&
                DataManager.getInstance().getUser().getAaa() == 3) {
            mMenuSS.add(2);
        }

        mMenuSS.add(4);
        mMenuSS.add(3);
        mMenuSS.add(8);
        mMenuSS.add(10);
        mMenuSS.add(7);
        mMenuSS.add(9);

        mMenuCC.add(5);

        if (DataManager.getInstance().getUser() != null &&
                DataManager.getInstance().getUser().getAaa() == 3) {
            mMenuCC.add(2);
        }

        mMenuCC.add(4);
        mMenuCC.add(3);
        mMenuCC.add(6);
        mMenuCC.add(11);
        mMenuCC.add(8);
        mMenuCC.add(10);
        mMenuCC.add(7);
        mMenuCC.add(9);

        mMenuPP.add(5);
        mMenuPP.add(3);
        mMenuPP.add(6);
        mMenuPP.add(11);
        mMenuPP.add(8);
        mMenuPP.add(10);
        mMenuPP.add(7);
        mMenuPP.add(9);
    }

    private void setOptions() {
        mOptions = new ArrayList<Option>();
        mOptions.add(new Option(0, R.string.login, null, R.drawable.ic1, false));
        mOptions.add(new Option(1, R.string.consulta_trafico,
                Constants.PROTOCOL + "://" + Constants.DOMAIN + "/" + Constants.API_PATH + "/trafico.action",
                R.drawable.ic7, true));
        mOptions.add(new Option(2, R.string.estado_cuenta,
                Constants.PROTOCOL + "://" + Constants.DOMAIN + "/" + Constants.API_PATH + "/cuenta.action",
                R.drawable.ic1, true));
        mOptions.add(new Option(3, R.string.bolsas,
                Constants.PROTOCOL + "://" + Constants.DOMAIN + "/"+ Constants.API_PATH +"/bolsas.action",
                R.drawable.ic6, false));
        mOptions.add(new Option(4, R.string.plan,
                Constants.PROTOCOL + "://" + Constants.DOMAIN + "/"+ Constants.API_PATH +"/planes.action",
                R.drawable.ic8, true));
        mOptions.add(new Option(5, R.string.consulta_saldo,
                "http://" + Constants.DOMAIN + "/" + Constants.API_PATH + "/menu.action",
                R.drawable.ic7, true));
        mOptions.add(new Option(6, R.string.recargas,
                "http://m.entel.cl/movil/appmanager/movil/sitio?_nfpb=true&_pageLabel=P69800258881375760051009&recarga=WebPay",
                R.drawable.ic2, false));
        mOptions.add(new Option(7, R.string.tiendas,
                "http://m.entel.cl/movil/appmanager/movil/home?_nfpb=true&_pageLabel=P8200011391412281212359#wlp_P8200011391412281212359",
                R.drawable.ic10, false));
        mOptions.add(new Option(8, R.string.planes_tarifas,
                "http://m.entel.cl/movil/appmanager/movil/home?_nfpb=true&_pageLabel=P81200119611410532589965&apps=1",
                R.drawable.ic9, false));
        mOptions.add(new Option(9, R.string.beneficios,
                "http://m.entel.cl/movil/appmanager/movil/home?_nfpb=true&_pageLabel=P87200172351434549742583#wlp_P87200172351434549742583",
                R.drawable.ic11, false));
        mOptions.add(new Option(10, R.string.ayuda,
                "http://m.entel.cl/movil/appmanager/movil/home?_nfpb=true&_pageLabel=P81200219611410532612358&apps=1",
                R.drawable.ic4, false));
        mOptions.add(new Option(11, R.string.presta_luka,
                "http://m.entel.cl/movil/appmanager/movil/home?_nfpb=true&_pageLabel=P87000228711434037549685#wlp_P87000228711434037549685",
                R.drawable.ic12, false));
    }

    public class Option {
        private int id;
        private int titleId;
        private String url;
        private int icon;
        private boolean pullToRefresh;

        private Option(int id, int titleId, String url, int icon, boolean pullToRefresh) {
            this.id = id;
            this.titleId = titleId;
            this.url = url;
            this.icon = icon;
            this.pullToRefresh = pullToRefresh;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getTitleId() {
            return titleId;
        }

        public void setTitleId(int titleId) {
            this.titleId = titleId;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getIcon() {
            return icon;
        }

        public void setIcon(int icon) {
            this.icon = icon;
        }

        public boolean isPullToRefresh() {
            return pullToRefresh;
        }
    }
}
