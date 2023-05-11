abstract class Animal{
  int? patas;
  void emitirSonido();
}

class Perro implements Animal{

  @override
  int? patas;

  @override
  void emitirSonido() {
    print("Guauuuu");
  }

  void testAsinc(){
    print('Antes de la petición');
    httpGet('https://api.nasa.com/aliens').then((data) {
          print(data.toUpperCase());
        });
    print('Fin del programa');
  }

  Future<String> httpGet(String url) {
    return Future.delayed(Duration(seconds: 3),
            () => 'Hola Mundo - 3 segundos');

  }


  Future<String> getNombre(String id) async {
    return '$id - Fernando';
  }

}