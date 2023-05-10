<?php

namespace App\Http\Controllers;

use App\Models\Facultad;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Log;

class FacultaControllerd extends Controller
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

        $facultades=Facultad::all();
        $mappedcollection = $facultades->map(function($facultad, $key) {
        return [
        'id' => $facultad->id,
        'nombrefac' => $facultad->nombrefac,
        'estado'=>$facultad->estado,
        'iniciales'=>$facultad->iniciales,

        ];
        });
        return response()->json(['success' => true,
        'data' => $mappedcollection,
        //'data' => Persona::all(),
        'message' => 'lista de facultades'], 200);
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
        Facultad::create($input);
        return response()->json(['success' => true,
        'data' => Facultad::all(),
        'message' => 'Lista de Facultad'], 200);
    }

    /**
     * Display the specified resource.
     */
    public function show(string $id)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     */
    public function edit(string $id)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, string $id)
    {
        Log::channel('stderr')->info($request);
        $input = $request->all();
        $facultad=Facultad::find($id);
        $facultad->nombrefac = $input['nombrefac'];
        $facultad->estado = $input['estado'];
        $facultad->iniciales = $input['iniciales'];

        $facultad->save();
        return response()->json(['success' => true,
        'data' => Facultad::all(),
        'message' => 'Lista de facultades'], 200);
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(string $id)
    {
        Log::channel('stderr')->info($id);
        Facultad::find($id)->delete();
        return response()->json(['success' => true,
        'data' => Facultad::all(),
        'message' => 'eliminado correctamente'], 200);
    }
}
