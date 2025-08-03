import { Component, OnInit } from '@angular/core';
import { UserService } from '../_services/user.service';
import {Observable} from "rxjs";
import {Product} from "../_model/product.model";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  message: string | undefined;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.forUser();
  }

  forUser() {
    this.userService.forUser().subscribe(
      (response) => {
        console.log(response);
        this.message = response;
      },
      (error)=>{
        console.log(error);
      }
    );
  }

  /* to ignore declaration/initialisation
  product : Observable<any> | undefined;
  // @ts-ignore
  productt : Observable<any> ;
  producttt$! : Observable<Array<Product>>; // variable qui termine avec "$" => Observable
   */
  //producttt$! : Observable<Array<Product>>; // variable qui termine avec "$" => Observable


  /*
  handleDelete(product:Product):any{
    if(confirm("Etes-vous sure?")){
      this.productService.deleteProduct(product).subscribe({
        next:value => {
          //this.getProducts();
          this.products=this.products.filter(p=>p.id!=product.productId);
        },
        error:err => {
          console.log(err);
        }
      })
    }
  }
  */

}
