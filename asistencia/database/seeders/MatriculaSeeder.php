<?php

namespace Database\Seeders;

use App\Models\Matricula;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;

class MatriculaSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        Matricula::create([
            'periodo_id'=>'1',
            'persona_id'=>'1',
            'estado'=>'matriculado'
        ]);

        Matricula::create([
            'periodo_id'=>'1',
            'persona_id'=>'2',
            'estado'=>'matriculado'
        ]);

        Matricula::create([
            'periodo_id'=>'1',
            'persona_id'=>'3',
            'estado'=>'matriculado'
        ]);

    }
}
