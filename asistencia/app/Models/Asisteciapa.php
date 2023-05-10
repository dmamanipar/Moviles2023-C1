<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Asisteciapa extends Model
{
    use HasFactory;
    protected $table = 'asisteciapas';
    public $timestamps = false;
    protected $primaryKey = 'id';
    protected $fillable = [
        'id',
        'fecha',
        'hora_reg',
        'latituda',
        "longituda",
        'tipo',
        'calificacion',
        'cui',
        'tipo_cui',
        'actividad_id'
    ];

    public function actividad(){
        return $this->belongsTo(Actividad::class);
    }
}
