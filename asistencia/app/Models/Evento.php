<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Evento extends Model
{
    use HasFactory;
    protected $table = 'eventos';


    public $timestamps = false;


    protected $primaryKey = 'id';


    protected $fillable = ['id', 'nom_evento', 'fecha', 'horai', 'min_toler',
    "telefono",'latitud', 'longitud','estado','evaluar','perfil_evento','offline'];
}
