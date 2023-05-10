<?php

namespace App\Http\Controllers;

use App\Models\Asisteciapa;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Log;

class AsisteciapaController extends Controller
{

    public function __construct() {
        $this->middleware('auth:api');
        }

    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        //Log::channel('stderr')->info("Si llega aqui");

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
        'data' => Persona::all(),
        'message' => 'lista de actividades'], 200);
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
        Asisteciapa::create($input);

        return response()->json(['success' => true,
        'data' => Asisteciapa::all(),
        'message' => 'Lista de Asisteciapa'], 200);
    }

    /**
     * Display the specified resource.
     */
    public function show(Asisteciapa $asisteciapa)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     */
    public function edit(Asisteciapa $asisteciapa)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, Asisteciapa $asisteciapa)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(Asisteciapa $asisteciapa)
    {
        Log::channel('stderr')->info($asisteciapa);
        Asisteciapa::find($id)->delete();
        return response()->json(['success' => true,
        'data' => Asisteciapa::all(),
        'message' => 'Lista de Asisteciapa'], 200);
    }
}
