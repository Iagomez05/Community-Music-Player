using System;
using System.IO;
using IniParser;
using IniParser.Model;
using IniParser.Parser;

namespace lectorIni
{
    class IniReader
    {
        static void Main(string[] args)
        {
            var parser = new FileIniDataParser();
            IniParser.Model.IniData data = parser.ReadFile("data1.ini");
        
            // Acceder a una sección y clave específica
            string value = data["SectionName"]["KeyName"];
            Console.WriteLine(value);
            Console.WriteLine("Prueba");
        }
    }
}

