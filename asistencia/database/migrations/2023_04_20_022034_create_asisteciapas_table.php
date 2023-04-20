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
        Schema::create('asisteciapas', function (Blueprint $table) {
            $table->id();
            $table->date('fecha');
            $table->time('hora_reg');
            $table->string('latituda',60);
            $table->string('longituda',60);
            $table->string('tipo',20);
            $table->integer('calificacion');
            $table->string('cui', 16);
            $table->string('tipo_cui', 12);
            $table->unsignedBigInteger('actividad_id');
            $table->foreign('actividad_id')->references('id')->on('actividads')->onDelete('cascade');
            //$table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('asisteciapas');
    }
};
