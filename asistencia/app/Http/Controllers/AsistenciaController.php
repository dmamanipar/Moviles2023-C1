<?php

namespace App\Http\Controllers;

use App\Models\Asistencia;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Log;

class AsistenciaController extends Controller
{
    //
    public function __construct()
    {
        $this->middleware('auth:api');
    }

    public function index()
    {
        $asistencias = Asistencia::all();
        $mappedcollection = $asistencias->map(function ($asistencia, $key) {
            return [
                'id' => $asistencia->id,
                'fecha' => $asistencia->fecha,
                'hora' => $asistencia->hora,
                'latituda' => $asistencia->latituda,
                'longituda' => $asistencia->longituda,
                'tipo' => $asistencia->tipo,
                'tipo_reg' => $asistencia->tipo_reg,
                'id_matricula' => $asistencia->id_matricula,
                'id_evento' => $asistencia->id_evento,
                'id_persona' => $asistencia->id_persona,
                'calificacion' => $asistencia->calificacion,
                'offlinex' => $asistencia->offlinex,
            ];
        });

        return response()->json([
            'success' => true,
            'data' => $mappedcollection,
            //'data' => Asistencia::all(),
            'message' => 'lista de asistencias'
        ], 200);
    }

    public function store(Request $request)
    {
        $input = $request->all();
        Asistencia::create($input);
        return response()->json([
            'success' => true,
            'data' => Asistencia::all(),
            'message' => 'Lista de asistencias'
        ], 200);
    }

    public function update(Request $request, $id)
    {
        $input = $request->all();
        $asistencia=Asistencia::find($id);
        $asistencia->fecha = $input['fecha'];
        $asistencia->hora = $input['hora'];
        $asistencia->latituda = $input['latituda'];
        $asistencia->longituda = $input['longituda'];
        $asistencia->tipo = $input['tipo'];
        $asistencia->tipo_reg = $input['tipo_reg'];
        $asistencia->id_matricula = $input['id_matricula'];
        $asistencia->id_evento = $input['id_evento'];
        $asistencia->id_persona = $input['id_persona'];
        $asistencia->calificacion = $input['calificacion'];
        $asistencia->offlinex = $input['offlinex'];

        $asistencia->save();
        return response()->json([
            'success' => true,
            'data' => Asistencia::all(),
            'message' => 'Lista de asistencias'
        ], 200);
    }


    public function destroy(Request $request, $id)
    {
        Log::channel('stderr')->info($id);
        Asistencia::find($id)->delete();
        return response()->json(['success' => true,
        'data' => Asistencia::all(),
        'message' => 'Lista de Actividad'], 200);
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
