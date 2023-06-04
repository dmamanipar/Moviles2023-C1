<?php

namespace App\Http\Controllers;

use App\Models\Actividad;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Log;


class ActividadController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function __construct() {
        $this->middleware('auth:api');
        }


    public function index()
    {
        Log::channel('stderr')->info("Si llega aqui");

        $actividades=Actividad::all();
        $mappedcollection = $actividades->map(function($actividad, $key) {
        return [
        'id' => $actividad->id,
        'periodo_id' => $actividad->periodo_id,
        'nombre_actividad'=>$actividad->nombre_actividad,
        'fecha'=>$actividad->fecha,
        'horai'=>$actividad->horai,
        'min_toler'=>$actividad->min_toler,
        'latitud'=>$actividad->latitud,
        'longitud'=>$actividad->longitud,
        'estado'=>$actividad->estado,
        'evaluar'=>$actividad->evaluar,
        'user_create'=>$actividad->user_create,
        'asistenciapas'=>$actividad->asisteciapas,
        ];
        });
        return response()->json(['success' => true,
        'data' => $mappedcollection,
        //'data' => Persona::all(),
        'message' => 'lista de actividades'], 200);
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, $id)
    {
        Log::channel('stderr')->info($request);
        $input = $request->all();
        $actividad=Actividad::find($id);
        $actividad->periodo_id = $input['periodo_id'];
        $actividad->nombre_actividad = $input['nombre_actividad'];
        $actividad->fecha = $input['fecha'];
        $actividad->horai = $input['horai'];
        $actividad->min_toler = $input['min_toler'];
        $actividad->latitud = $input['latitud'];
        $actividad->longitud = $input['longitud']; //estado
        $actividad->estado = $input['estado'];
        $actividad->evaluar = $input['evaluar'];
        $actividad->user_create = $input['user_create'];

        $actividad->save();
        return response()->json(['success' => true,
        'data' => Actividad::all(),
        'message' => 'Lista de Actividades'], 200);
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
        Log::channel('stderr')->info($request);
        Actividad::create($input);

        return response()->json(['success' => true,
        'data' => Actividad::all(),
        'message' => 'Lista de Actividad'], 200);
    }

    /**
     * Display the specified resource.
     */
    public function show(Actividad $actividad)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     */
    public function edit(Actividad $actividad)
    {
        //
    }



    /**
     * Remove the specified resource from storage.
     */
    public function destroy(Request $request, $id)
    {
        Log::channel('stderr')->info($id);
        Actividad::find($id)->delete();
        return response()->json(['success' => true,
        'data' => Actividad::all(),
        'message' => 'Lista de Actividad'], 200);
    }
}
