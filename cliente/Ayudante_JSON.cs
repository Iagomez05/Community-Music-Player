using System.Text.Json;
    public class Ayudante_JSON
    {
        public Ayudante_JSON()
        {
            
        }

        public string Serialice_Modelo<T>(T modelo) {

            try
            {
                string json = JsonSerializer.Serialize(modelo);

                return json;
            }
            catch (Exception e)
            {

                throw new Exception(e.Message);
            }
        }

        public T Deserialize_Modelo<T>(string json) {
            return JsonSerializer.Deserialize<T>(json)
                ?? throw new JsonException("The JSON payload could not be deserialized.");
        }
    }
