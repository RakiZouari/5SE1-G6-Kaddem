import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { contrat } from './Contrat';

@Injectable({
  providedIn: 'root'
})
export class NomDuServiceService {

 
  readonly API_URL = 'http://192.168.33.10:8089/Kaddem/contrat';

  constructor(private httpClient: HttpClient) { }
  getAllContrat() {
    return this.httpClient.get(`${this.API_URL}/retrieve-all-contrats`)
  }
  addContrat(contrat : any) {
    return this.httpClient.post(`${this.API_URL}/add-contrat`, contrat)
  }
  editContrat(contrat : any){
    return this.httpClient.put(`${this.API_URL}/update-contrat`, contrat)
  }


  
}