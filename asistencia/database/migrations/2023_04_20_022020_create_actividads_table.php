<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('actividads', function (Blueprint $table) {
            $table->id();
            $table->unsignedBigInteger("periodo_id");
            $table->string('nombre_actividad', 60);
            $table->date('fecha');
            $table->time('horai');
            $table->time('min_toler');
            $table->string('latitud', 60);
            $table->string('longitud', 60);
            $table->string('estado', 8);
            $table->string('evaluar', 2);
            $table->string('user_create', 60);
            //$table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('actividads');
    }
};
