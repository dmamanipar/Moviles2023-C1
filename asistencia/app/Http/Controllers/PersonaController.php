<?php


namespace App\Http\Controllers;


use App\Models\Persona;
use Illuminate\Http\Request;


class PersonaController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function __construct() {
        $this->middleware('auth:api');


       }
    public function index(Request $request)
    {
        return response()->json(['success' => true,
        'data' => Persona::all(),
        'message' => 'lista de personas'], 200);
    }


    /**
     * Show the form for creating a new resource.
     */
    public function create()
    {
        //
    }


    /**
     * Store a newly created resource in storage.
     */
    public function store(Request $request)
    {
        $input = $request->all();
        Persona::create($input);
        return response()->json(['success' => true,
        'data' => Persona::all(),
        'message' => 'Lista de personas'], 200);
    }


    /**
     * Display the specified resource.
     */
    public function show(Persona $persona)
    {
        //
    }


    /**
     * Show the form for editing the specified resource.
     */
    public function edit(Persona $persona)
    {
        //
    }


    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, Persona $persona)
    {
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


    /**
     * Remove the specified resource from storage.
     */
    public function destroy(Persona $persona)
    {
        $persona->delete();
        return response()->json(['success' => true,


        'data' => Persona::all(),
        'message' => 'Lista de personas'], 200);


    }
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
