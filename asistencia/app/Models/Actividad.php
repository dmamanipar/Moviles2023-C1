<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Actividad extends Model
{
    use HasFactory;
    protected $table = 'actividads';
    public $timestamps = false;
    protected $primaryKey = 'id';
    protected $fillable = [
        'id', 'periodo_id', 'nombre_actividad', 'fecha',
        'horai', "min_toler",
        'latitud', 'longitud', 'estado', 'evaluar', 'user_create'
    ];

    //Relacion de 1 a muchos
    public function asisteciapas()
    {
        return $this->hasMany(Asisteciapa::class);
    }
}
