<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Evento extends Model
{
    use HasFactory;
    protected $table = 'asistencia';
    public $timestamps = false;
    protected $primaryKey = 'id';
    protected $foreignKey = ['id_matricula', 'id_evento'];
    protected $fillable = ['id', 'nom_evento','fecha', 'horai', 'min_toler', 'latitud', 'longitud', "estado", 'evaluar', 'perfil_evento', 'periodo_id'];

    public function periodos()
    {
        return $this->belongsTo(Periodo::class);
    }

    public function asistencias()
    {
        return $this->hasMany(Asistencia::class);
    }
}
