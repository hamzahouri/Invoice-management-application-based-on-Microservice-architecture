import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-order-details',
  templateUrl: './order-details.component.html',
  styleUrls: ['./order-details.component.css']
})
export class OrderDetailsComponent implements OnInit {

  ordersDetails:any;
  orderId!:number;

  constructor(private http:HttpClient,
              private router:Router,
              private route:ActivatedRoute) {
    this.orderId=route.snapshot.params['orderId'];
  }

  ngOnInit(): void {
    this.http.get("http://localhost:8888/BILLING-SERVICE/bills/search/byCustomerId?customerId="+this.orderId).subscribe({
      next : (data)=>{
        this.ordersDetails=data;
        console.log(data);
      },
      error :(err)=>{}

    });
  }

}
