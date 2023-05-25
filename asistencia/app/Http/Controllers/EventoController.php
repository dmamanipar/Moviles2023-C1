<?php

namespace App\Http\Controllers;

use App\Models\Evento;
use Illuminate\Http\Request;

class EventoController extends Controller
{
   /**
     * Display a listing of the resource.
     */
    public function __construct() {
        $this->middleware('auth:api');


       }
    public function index()
    {
        $eventos=Evento::all();
        $mappedcollection = $eventos->map(function($evento, $key) {
        return [
        'id' => $evento->id,
        'nom_evento' => $evento->nom_evento,
        'fecha'=>$evento->fecha,
        'horai'=>$evento->horai,
        'min_toler'=>$evento->min_toler,
        'latitud'=>$evento->latitud,
        'longitud'=>$evento->longitud,
        'estado'=>$evento->estado,
        'evaluar'=>$evento->evaluar,
        'perfil_evento'=>$evento->perfil_evento,
        'offline'=>$evento->offline,
    ];
        });
        return response()->json(['success' => true,
        'data' => $mappedcollection,
        //'data' => Persona::all(),
        'message' => 'lista de eventos'], 200);
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
        Evento::create($input);
        return response()->json(['success' => true,
        'data' => Evento::all(),
        'message' => 'Lista de eventos'], 200);
    }


    /**
     * Display the specified resource.
     */
    public function show(Evento $evento)
    {
        //
    }


    /**
     * Show the form for editing the specified resource.
     */
    public function edit(Evento $evento)
    {
        //
    }


    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, Evento $evento)
    {
        $input = $request->all();
        $evento->nom_evento = $input['nom_evento'];
        $evento->fecha = $input['fecha'];
        $evento->horai = $input['horai'];
        $evento->min_toler = $input['min_toler'];
        $evento->latitud = $input['latitud'];
        $evento->longitud = $input['longitud'];
        $evento->estado = $input['estado'];
        $evento->evaluar = $input['evaluar'];
        $evento->perfil_evento = $input['perfil_evento'];
        $evento->offline = $input['offline'];
        $evento->save();


        return response()->json(['success' => true,


        'data' => Evento::all(),
        'message' => 'Lista de eventos'], 200);


    }


    /**
     * Remove the specified resource from storage.
     */
    public function destroy($id)
    {
        Evento::find($id)->delete();
        return response()->json(['success' => true,
        'data' => Evento::all(),
        'message' => 'Lista de eventos'], 200);
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
