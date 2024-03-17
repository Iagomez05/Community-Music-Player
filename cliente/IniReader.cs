using System;
using System.IO;
using IniParser;
using IniParser.Model;
using IniParser.Parser;

namespace lectorIni
{
    public class IniReader
    {
        private FileIniDataParser parser;

        public IniReader()
        {
            parser = new FileIniDataParser();
        }

        public string LeerConfiguracion(string rutaArchivo, string seccion, string clave)
        {
            IniData data = parser.ReadFile(rutaArchivo);
            return data[seccion][clave];
        }
    }

}

