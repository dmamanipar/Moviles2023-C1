<?php

namespace App\Http\Controllers;

use App\Http\Requests\FacultadPostRequest;
use App\Models\Facultad;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Log;

class FacultadController extends Controller
{
    /**
     * Display a listing of the resource.
     */



    public function index(){



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
        'data' => Facultad::all(),
        //'data' => Persona::all(),
        'message' => 'lista de facultades'], 200);

    }

    public function show(Facultad $facultad){
       }
    public function store(Request $request){

        $input = $request->all();
        Log::channel('stderr')->info($request);
        Facultad::create($input);

        return response()->json(['success' => true,
        'data' => Facultad::all(),
        'message' => 'store'], 200);

    }

    public function update(Request $request, $id){

        Log::channel('stderr')->info($request);
        $input = $request->all();
        $facultad=Facultad::find($id);
        $facultad->nombrefac = $input['nombrefac'];
        $facultad->estado = $input['estado'];
        $facultad->iniciales = $input['iniciales'];


        $facultad->save();
        return response()->json(['success' => true,
        'data' => Facultad::all(),
        'message' => 'record saved successfully!'], 200);
    }

    public function destroy(facultad $facultad,$id){
        Log::channel('stderr')->info($id);
        Facultad::find($id)->delete();
        return response()->json(['success' => true,
        'data' => Facultad::all(),
        'message' => 'Lista de fac'], 200);
    }
}
