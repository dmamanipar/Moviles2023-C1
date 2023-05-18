<?php

namespace App\Http\Controllers;

use App\Http\Requests\MatriculaPostRequest;
use App\Models\Matricula;
use Illuminate\Http\Request;

class MatriculaController extends Controller
{

    public function __construct()
    {
        $this->middleware('auth:api');
    }

    public function index()
    {
        $matriculas = Matricula::all();
        //return $tags;
        return response()->json($matriculas);
    }

    public function show(Matricula $matricula)
    {
        $matricula = Matricula::find($matricula);
        return response()->json($matricula);
    }

    public function store(MatriculaPostRequest $request)
    {
        $matricula = Matricula::create($request->all());

        return response()->json([
            'message' => "record saved successfully!",
            'name' => $matricula
        ], 200);
    }

    public function update(MatriculaPostRequest $request, Matricula $matricula)
    {
        $matricula->update($request->all());

        return response()->json([
            'message' => "record updated successfully!",
            'name' => $matricula
        ], 200);
    }

    public function destroy(Matricula $matricula)
    {
        $matricula->delete();
        return response()->json([
            'message' => "record deleted successfully!",
        ], 200);
    }
}
