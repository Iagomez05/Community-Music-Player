using System;
using System.IO;
using IniParser;
using IniParser.Model;
using IniParser.Parser;
namespace lectorIni
{
    // Clase IniReader diseñada para leer datos de archivos INI.
    public class IniReader
    {
        // Instancia de FileIniDataParser para analizar archivos INI.
        private FileIniDataParser parser;

        // Constructor de IniReader que inicializa el parser.
        public IniReader()
        {
            // Creando una nueva instancia de FileIniDataParser.
            parser = new FileIniDataParser();
        }
        
        // Método para leer una configuración específica de un archivo INI.
        // rutaArchivo: Ruta del archivo INI.
        // seccion: Nombre de la sección dentro del archivo INI.
        // clave: Nombre de la clave dentro de la sección.
        // Retorna el valor de la clave especificada.
        public string LeerConfiguracion(string rutaArchivo, string seccion, string clave)
        {
            // Lectura del archivo INI y almacenamiento de los datos en 'data'.
            IniData data = parser.ReadFile(rutaArchivo);
            // Retornando el valor de la clave especificada dentro de la sección.
            return data[seccion][clave];
        }
    }
}