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

    public function index(){
        $facultads=Facultad::all();
        //return $matriculas;
        return response()->json($facultads);
    }

    public function show(matricula $facultad){
        $matricula=matricula::find($matricula);
        return response()->json($matricula);
    }
    public function store(MatriculaPostRequest $request){
        $matricula = Matricula::create($request->all());

        return response()->json([
            'message' => "record saved successfully!",
            'name' => $matricula
        ], 200);
    }

    public function update(MatriculaPostRequest $request, matricula $matricula){
        $matricula->update($request->all());

        return response()->json([
            'message' => "record updated successfully!",
            'name' => $matricula
        ], 200);
    }

    public function destroy(matricula $matricula){
        $matricula->delete();
        return response()->json([
            'message' => "record deleted successfully!",
        ], 200);
    }
}
