<?php

namespace App\Http\Controllers;

use App\Http\Requests\FacultadPostRequest;
use App\Models\Facultad;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Log;

class FacultaControllerd extends Controller
{
    /**
     * Display a listing of the resource.
     */



    public function index(){
        $facultads=Facultad::all();
        //return $matriculas;
        return response()->json($facultads);
    }

    public function show(Facultad $facultad){
        $facultad=Facultad::find($facultad);
        return response()->json($facultad);
    }
    public function store(FacultadPostRequest $request){
        $facultad = Facultad::create($request->all());

        return response()->json([
            'message' => "record saved successfully!",
            'name' => $facultad
        ], 200);
    }

    public function update(FacultadPostRequest $request, facultad $facultad){
        $facultad->update($request->all());

        return response()->json([
            'message' => "record updated successfully!",
            'name' => $facultad
        ], 200);
    }

    public function destroy(facultad $facultad){
        $facultad->delete();
        return response()->json([
            'message' => "record deleted successfully!",
        ], 200);
    }
}
