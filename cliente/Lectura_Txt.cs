using System.Text;

namespace Capa_Acceso_Datos.Txt
{
    public class Lectura_Txt
    {
        
        public Lectura_Txt()
        
        {

        }

        public string Lee_Archivo(string rutaArchivo) {

            try
            {
                string contenido;
                using (StreamReader streamReader = new StreamReader(rutaArchivo, Encoding.UTF8))
                {
                    contenido = streamReader.ReadToEnd();
                }

                return contenido;
            }
            catch (Exception e)
            {
                Console.WriteLine("Error al leer archivo");
                throw new Exception(e.Message);
            }
        }
    }
}
