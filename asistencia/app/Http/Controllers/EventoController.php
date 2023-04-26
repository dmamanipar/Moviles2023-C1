<?php

namespace App\Http\Controllers;

use App\Models\Evento;
use Illuminate\Http\Request;

class EventoController extends Controller
{
    public function __construct()
    {
        $this->middleware('auth:api');
    }

    public function index()
    {
        $eventos = Evento::all();
        $mappedcollection = $eventos->map(function ($evento, $key) {
            return [
                'id' => $evento->id,
                'nom_evento' => $evento->nom_evento,
                'horai' => $evento->horai,
                'min_toler' => $evento->min_toler,
                'latitud' => $evento->latitud,
                'longitud' => $evento->longitud,
                'estado' => $evento->estado,
                'evaluar' => $evento->evaluar,
                'perfil_evento' => $evento->perfil_evento,
                'offline' => $evento->offline,
                'periodo_id' => $evento->periodo_id
            ];
        });

        return response()->json([
            'success' => true,
            'data' => $mappedcollection,
            //'data' => Asistencia::all(),
            'message' => 'lista de eventos'
        ], 200);
    }

    public function store(Request $request)
    {
        $input = $request->all();
        Evento::create($input);
        return response()->json([
            'success' => true,
            'data' => Evento::all(),
            'message' => 'Lista de eventos'
        ], 200);
    }

    public function update(Request $request, Evento $evento)
    {
        $input = $request->all();
        $evento->nom_evento = $input['nom_evento'];
        $evento->fecha = $input['fecha'];
        $evento->horai = $input['horai'];
        $evento->min_toler = $input['min_toler'];
        $evento->latituda = $input['latituda'];
        $evento->longitud = $input['longitud'];
        $evento->estado = $input['estado'];
        $evento->evaluar = $input['evaluar'];
        $evento->perfil_evento = $input['perfil_evento'];
        $evento->offline = $input['offline'];
        $evento->periodo_id = $input['periodo_id'];

        $evento->save();
        return response()->json([
            'success' => true,
            'data' => Evento::all(),
            'message' => 'Lista de evento'
        ], 200);
    }


    public function destroy(Evento $evento)
    {
        $evento->delete();
        return response()->json([
            'success' => true,
            'data' => Evento::all(),
            'message' => 'Lista de eventos'
        ], 200);
    }

    //Manejo de Error
    public function sendError($error, $errorMessages = [], $code = 404)
    {
        $response = [
            'success' => false,
            'message' => $error,
        ];
        if (!empty($errorMessages)) {
            $response['data'] = $errorMessages;
        }
        return response()->json($response, $code);
    }
}
