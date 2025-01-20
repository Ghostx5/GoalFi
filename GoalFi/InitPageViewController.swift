//
//  InitPageViewController.swift
//  GoalFi
//
//  Created by Vijay Venkatesan on 1/19/25.
//

import UIKit
import FirebaseAuth

class InitPageViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        
        // Do any additional setup after loading the view.
        Auth.auth().addStateDidChangeListener{auth, user in
            if let user = user{
                print("User is signed in.")
                self.performSegue(withIdentifier: "dontPassGo", sender: self)
            }
            else{
                print("Nobody's signed in!")
            }
                
            }
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}
