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

        $actividades=Asisteciapa::all();
        $mappedcollection = $actividades->map(function($actividad, $key) {
        return [
        'id' => $actividad->id,
        'fecha' => $actividad->fecha,
        'hora_reg'=>$actividad->hora_reg,
        'latituda'=>$actividad->latituda,
        'longituda'=>$actividad->longituda,
        'tipo'=>$actividad->tipo,
        'calificacion'=>$actividad->calificacion,
        'cui'=>$actividad->cui,
        'tipo_cui'=>$actividad->tipo_cui,
        'actividad_id'=>$actividad->actividad_id,
        ];
        });
        return response()->json(['success' => true,
        'data' => $mappedcollection,
        //'data' => Persona::all(),
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
    public function update(Request $request, $id)
    {
        Log::channel('stderr')->info($request);
        $input = $request->all();
        $actividad=Asisteciapa::find($id);
        $actividad->fecha = $input['fecha'];
        $actividad->hora_reg = $input['hora_reg'];
        $actividad->latituda = $input['latituda'];
        $actividad->longituda = $input['longituda'];
        $actividad->tipo = $input['tipo'];
        $actividad->calificacion = $input['calificacion'];
        $actividad->cui = $input['cui'];
        $actividad->tipo_cui = $input['tipo_cui'];
        $actividad->actividad_id = $input['actividad_id'];

        $actividad->save();
        return response()->json(['success' => true,
        'data' => Asisteciapa::all(),
        'message' => 'Lista de Asistencias'], 200);
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(Request $request, $id)
    {
        Log::channel('stderr')->info($id);
        Asisteciapa::find($id)->delete();
        return response()->json(['success' => true,
        'data' => Asisteciapa::all(),
        'message' => 'Lista de Asisteciapa'], 200);
    }
}
