<?php

namespace Database\Seeders;

use App\Models\Facultad;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;

class FacultadSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run()
    {
        Facultad::create([

            'nombrefac'=>'Facultad Ingenieria y Arquitectura',
            'estado'=>'Activa',
            'iniciales' =>'FIA'
            //$table->string('nombrefac');
            //$table->string('estado');
            //$table->string('iniciales');
        ]);
        Facultad::create([

            'nombrefac'=>'BioMedicas',
            'estado'=>'Activa',
            'iniciales' =>'BIO'
            //$table->string('nombrefac');
            //$table->string('estado');
            //$table->string('iniciales');
        ]);

    }
}
