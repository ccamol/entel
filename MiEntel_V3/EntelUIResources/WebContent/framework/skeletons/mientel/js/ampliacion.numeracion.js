function ampliacionNumerica(region, fechaActual) {
    var longitud = 6;

    //Formato de la fecha dd-mm-yyyy, 00-00-0000 implica que ya esta en funcionamiento
    var regiones = {
        2  : {longitud: 7, fecha: '24-11-2012', ampliacion: 8},
		7  : {longitud: 8, fecha: '00-00-0000', ampliacion: 8},
        8  : {longitud: 8, fecha: '00-00-0000', ampliacion: 8},
        9  : {longitud: 8, fecha: '00-00-0000', ampliacion: 8},
        32 : {longitud: 7, fecha: '00-00-0000', ampliacion: 7},
        33 : {longitud: 6, fecha: '18-05-2013', ampliacion: 7},
        34 : {longitud: 6, fecha: '18-05-2013', ampliacion: 7},
        35 : {longitud: 6, fecha: '18-05-2013', ampliacion: 7},
        41 : {longitud: 7, fecha: '00-00-0000', ampliacion: 7},
        42 : {longitud: 6, fecha: '11-05-2013', ampliacion: 7},
        43 : {longitud: 6, fecha: '11-05-2013', ampliacion: 7},
        45 : {longitud: 6, fecha: '23-03-2013', ampliacion: 7},
        51 : {longitud: 6, fecha: '15-06-2013', ampliacion: 7},
        52 : {longitud: 6, fecha: '08-06-2013', ampliacion: 7},
        53 : {longitud: 6, fecha: '08-06-2013', ampliacion: 7},
        55 : {longitud: 6, fecha: '06-04-2013', ampliacion: 7},
        57 : {longitud: 6, fecha: '23-03-2013', ampliacion: 7},
        58 : {longitud: 6, fecha: '20-10-2012', ampliacion: 7},
        61 : {longitud: 6, fecha: '13-04-2013', ampliacion: 7},
        63 : {longitud: 6, fecha: '25-05-2013', ampliacion: 7},
        64 : {longitud: 6, fecha: '25-05-2013', ampliacion: 7},
        65 : {longitud: 6, fecha: '25-05-2013', ampliacion: 7},
        67 : {longitud: 6, fecha: '13-04-2013', ampliacion: 7},
        71 : {longitud: 6, fecha: '04-05-2013', ampliacion: 7},
        72 : {longitud: 6, fecha: '20-04-2013', ampliacion: 7},
        73 : {longitud: 6, fecha: '04-05-2013', ampliacion: 7},
        75 : {longitud: 6, fecha: '04-05-2013', ampliacion: 7}
    };

    if (regiones[region] != undefined){
          var fechaRegion = parseDate(regiones[region].fecha);
          if ((fechaActual - fechaRegion.getTime() / 1000) < 0){
             longitud = regiones[region].longitud;
          } else {
              longitud = regiones[region].ampliacion;
          }
      } else {
          longitud = 6;
      }

    return longitud;
}

function parseDate(input) {
    var parts = input.match(/(\d+)/g);
    // new Date(year, month [, date [, hours[, minutes[, seconds[, ms]]]]])
    return new Date(parts[2], parts[1]-1, parts[0]); // months are 0-based
}