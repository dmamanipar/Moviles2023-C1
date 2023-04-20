<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Asisteciapa extends Model
{
    use HasFactory;

    public function actividad(){
        return $this->belongsTo(Actividad::class);
    }
}
