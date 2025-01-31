//
//  LoginViewController.swift
//  GoalFi
//
//  Created by Vijay Venkatesan on 1/17/25.
//

import UIKit
import Firebase
import FirebaseAuth
import Toast

class LoginViewController: UIViewController {

    @IBOutlet weak var emailTextField: UITextField!
    @IBOutlet weak var passwordTextField: UITextField!
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }
    @IBAction func loginClicked(_ sender: UIButton) {
        guard let email = emailTextField.text else {return}
        guard let password = passwordTextField.text else {return}
        
        Auth.auth().signIn(withEmail: email, password: password) { firebaseResult, error in
            if let e = error {
                print("Error")
                let message = e.localizedDescription
                self.view.makeToast("Login Failed: " + message, duration: 5.0, position: .top)
            }
            else {
                self.view.makeToast("Logged in. Welcome Back!", duration: 5.0, position: .top)
                let emailStripped = email
                    .replacingOccurrences(of: "@", with: "")
                    .replacingOccurrences(of: ".", with: "")
                guard let userID = UserDefaults.standard.object(forKey: "uniqueDeviceID") else{return}
                let userDefaultsKey = "uniqueDeviceID"
                UserDefaults.standard.set(emailStripped, forKey: userDefaultsKey)
                print("Success! User ID is: " + emailStripped )

                self.performSegue(withIdentifier: "goToHome", sender: self)
                    
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
