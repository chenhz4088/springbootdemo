package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class main {
    public static void main(String[] args) {
//        SpringApplication.run(main.class,args);
        int ap[] = {13,2,3,4,7,6,5};
        for(int i = 0;i <ap.length-1;i++ ){
             for(int j=0;j<ap.length-1-i;j++){
                 if(ap[j] > ap[j+1]){
                    int temp =  ap[j+1];
                    ap[j+1] = ap[j];
                    ap[j] = temp;
                 }
             }
        }
        for(int i = 0;i <=ap.length-1;i++ ) {
            System.out.println(ap[i]);
        }

        int flag = 4;
        int length = ap.length-1;
        int start =0;
        while(start<=length){
            int mid = (start + length) / 2;
            if(flag == ap[mid]){
                System.out.println(mid);
                break;
            }
             if(flag>ap[mid]){
                start = mid+1;
            }
            if(flag<ap[mid]){
                length =mid -1;
            }
        }
    }
}



