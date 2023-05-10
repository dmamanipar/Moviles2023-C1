<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Facultad extends Model
{
    use HasFactory;


    protected $guarded = ['id'];

    public function escuelas()
    {
        return $this->hasMany(Escuela::class);
    }
}
