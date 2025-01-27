//
//  RegisterAccViewController.swift
//  GoalFi
//
//  Created by Vijay Venkatesan on 1/17/25.
//

import UIKit
import Firebase
import FirebaseAuth
import FirebaseDatabase
class RegisterAccViewController: UIViewController {

    @IBOutlet weak var emailTextField: UITextField!
    @IBOutlet weak var passwordTextField: UITextField!
    let databaseRef = Database.database().reference()

    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }
    
    @IBAction func registerClicked(_ sender: UIButton) {
        guard let email = emailTextField.text else {return}
        guard let password = passwordTextField.text else {return}
        Auth.auth().createUser(withEmail: email, password: password) {firebaseResult, error in
            if let e = error {
                print("Error")
            }
            else {
                let emailStripped = email
                    .replacingOccurrences(of: "@", with: "")
                    .replacingOccurrences(of: ".", with: "")
                guard let userID = UserDefaults.standard.object(forKey: "uniqueDeviceID") else {return}
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
