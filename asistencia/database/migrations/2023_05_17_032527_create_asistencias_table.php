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
        Schema::create('asistencias', function (Blueprint $table) {
            $table->id();
            $table->date("fecha");
            $table->time('hora');
            $table->string('latituda', 60);
            $table->string('longituda', 60);
            $table->string('tipo', 20);
            $table->string('tipo_reg', 12);
            $table->unsignedBigInteger('id_matricula');
            $table->unsignedBigInteger('id_evento');
            $table->string('id_persona', 10);
            $table->unsignedBigInteger('calificacion');
            $table->string('offlinex', 5);
            $table->foreign('id_matricula')->references('id')->on('matricula')->onDelete('cascade');
            $table->foreign('id_evento')->references('id')->on('evento')->onDelete('cascade');
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('asistencias');
    }
};
