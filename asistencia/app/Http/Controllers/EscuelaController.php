<?php

namespace App\Http\Controllers;

use App\Models\Escuela;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Log;

class EscuelaController extends Controller
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

        $escuelas=Escuela::all();
        $mappedcollection = $escuelas->map(function($escuela, $key) {
        return [
        'id' => $escuela->id,
        'nombreeap' => $escuela->nombreeap,
        'estado'=>$escuela->estado,
        'inicialeseap'=>$escuela->inicialeseap,
        'facultad_nom'=>$escuela->facultad_nom,
        ];
        });
        return response()->json(['success' => true,
        'data' => $mappedcollection,
        //'data' => Persona::all(),
        'message' => 'Lista de Escuelas'], 200);
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, Escuela $escuela)
    {
        $input = $request->all();
        $escuela->nombreeap = $input['nombreeap'];
        $escuela->estado = $input['estado'];
        $escuela->inicialeseap = $input['inicialeseap'];
        $escuela->facultad_nom = $input['facultad_nom'];
        $escuela->save();
        return response()->json(['success' => true,
        'data' => Escuela::all(),
        'message' => 'Lista de Escuelas'], 200);
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
        Escuela::create($input);
        return response()->json(['success' => true,
        'data' => Escuela::all(),
        'message' => 'Lista de Escuelas'], 200);
    }

    /**
     * Display the specified resource.
     */
    public function show(Escuela $escuela)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     */
    public function edit(Escuela $escuela)
    {
        //
    }



    /**
     * Remove the specified resource from storage.
     */
    public function destroy(Escuela $escuela)
    {
        $escuela->delete();
        return response()->json(['success' => true,
        'data' => Escuela::all(),
        'message' => 'Lista de Escuelas'], 200);
    }
}
