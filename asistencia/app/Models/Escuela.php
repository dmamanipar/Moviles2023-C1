<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Escuela extends Model
{
    use HasFactory;
    protected $table = 'escuelas';
    public $timestamps = false;
    protected $primaryKey = 'id';
    protected $fillable = [
        'id', 'nombreeap', 'estado', 'inicialeseap',
        'facultad_nom'
    ];

    //Relacion de 1 a muchos
    public function escuelas()
    {
        return $this->hasMany(Escuela::class);
    }
}
