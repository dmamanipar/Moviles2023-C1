<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Asistencia extends Model
{
    use HasFactory;
    protected $table = 'asistencias';
    public $timestamps = false;
    protected $primaryKey = 'id';
    protected $foreignKey = ['id_matricula', 'id_evento'];
    protected $fillable = ['id', 'fecha', 'hora', 'latituda', 'longituda', "tipo", 'tipo_reg', 'id_matricula', 'id_evento', 'id_persona', 'calificacion', 'offlinex'];

    public function matricula()
    {
        return $this->belongsTo(Matricula::class);
    }

    public function evento()
    {
        return $this->belongsTo(Evento::class);
    }
}
