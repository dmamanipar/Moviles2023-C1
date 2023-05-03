<?php

namespace App\Http\Controllers;

use App\Models\Persona;
use Illuminate\Http\Request;

class PersonaController extends Controller
{
    public function __construct() {
         $this->middleware('auth:api');

        }

        public function index(Request $request){
        return response()->json(['success' => true,
        'data' => Persona::all(),
        'message' => 'lista de personas'], 200);
}

        public function store(Request $request){
        $input = $request->all();
        Persona::create($input);
        return response()->json(['success' => true,
        'data' => Persona::all(),
        'message' => 'Lista de personas'], 200);
        }

        public function update(Request $request, Persona $persona){
        $input = $request->all();
        $persona->dni = $input['dni'];
        $persona->nombre = $input['nombre'];
        $persona->apellido_paterno = $input['apellido_paterno'];
        $persona->apellido_materno = $input['apellido_materno'];
        $persona->telefono = $input['telefono'];
        $persona->correo = $input['correo'];
        $persona->genero = $input['genero'];
        $persona->save();

        return response()->json(['success' => true,

        'data' => Persona::all(),
        'message' => 'Lista de personas'], 200);

        }

        public function destroy(Persona $persona){
        $persona->delete();
        return response()->json(['success' => true,

        'data' => Persona::all(),
        'message' => 'Lista de personas'], 200);

        } //
        public function sendError($error, $errorMessages = [], $code = 404){
            $response = [
            'success' => false,
            'message' => $error,
            ];
            if(!empty($errorMessages)){
            $response['data'] = $errorMessages;
            }
            return response()->json($response, $code);

            }
}
