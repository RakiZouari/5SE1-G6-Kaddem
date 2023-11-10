import { Component, OnInit } from '@angular/core';
import { NomDuServiceService } from './nom-du-service.service';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { contrat } from './Contrat';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'kaddemFront';

  form: boolean = false;
  closeResult!: string;
  listContrats: any;
  contrat!:any;

  constructor(private contraService: NomDuServiceService, private modalService: NgbModal ) { }

  ngOnInit(): void {
    this.getAllContrat();
    console.log(this.listContrats)
    this.contrat={
      idContrat : null,
      dateDebutContrat: null,
      dateFinContrat:null,
      specialite : null,
      archived :null,
      montantContrat:null
    }
  }

  getAllContrat(){
    return this.contraService.getAllContrat().subscribe(res=>{
      this.listContrats = res;
    });
  }
  addContrat(c: any) {
    this.contraService.addContrat(c).subscribe(() => {
      this.getAllContrat();
      this.form = false;
    });
  }

  editContrat(contrat: contrat) {
    this.contraService.editContrat(contrat).subscribe();
  }

  open(content: any, action: any) {
    if (action != null)
      this.contrat = action
    else
      this.contrat = new contrat();
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

  cancel() {
    this.form = false;
  }
}