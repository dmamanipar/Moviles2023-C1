<?php

namespace App\Http\Controllers;

use App\Http\Requests\MatriculaPostRequest;
use App\Models\Matricula;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Log;

class MatriculaController extends Controller
{

    public function __construct()
    {
        $this->middleware('auth:api');
    }

    public function index()
    {
        Log::channel('stderr')->info("Si llega aqui");

        $matriculas=Matricula::all();
        $mappedcollection = $matriculas->map(function($matricula, $key) {
        return [
        'id' => $matricula->id,
        'periodo_id' => $matricula->periodo_id,
        'persona_id'=>$matricula->persona_id,
        'estado'=>$matricula->estado,
        ];
        });
        return response()->json(['success' => true,
        'data' => $mappedcollection,
        //'data' => Persona::all(),
        'message' => 'lista de matriculas'], 200);
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, $id)
    {
        Log::channel('stderr')->info($request);
        $input = $request->all();
        $matricula=Matricula::find($id);
        $matricula->periodo_id = $input['periodo_id'];
        $matricula->persona_id = $input['persona_id'];
        $matricula->estado = $input['estado'];

        $matricula->save();
        return response()->json(['success' => true,
        'data' => Matricula::all(),
        'message' => 'Lista de Matriculas'], 200);
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
        Matricula::create($input);

        return response()->json(['success' => true,
        'data' => Matricula::all(),
        'message' => 'Lista de Matriculas'], 200);
    }

    /**
     * Display the specified resource.
     */
    public function show(Matricula $matricula)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     */
    public function edit(Matricula $matricula)
    {
        //
    }



    /**
     * Remove the specified resource from storage.
     */
    public function destroy(Request $request, $id)
    {
        Log::channel('stderr')->info($id);
        Matricula::find($id)->delete();
        return response()->json(['success' => true,
        'data' => Matricula::all(),
        'message' => 'Lista de Matricula'], 200);
    }
}
