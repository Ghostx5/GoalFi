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
class RegisterAccViewController: UIViewController, UITextFieldDelegate {

    @IBOutlet weak var emailTextField: UITextField!
    @IBOutlet weak var passwordTextField: UITextField!
    @IBOutlet weak var nameTextField: UITextField!
    let databaseRef = Database.database().reference()

    override func viewDidLoad() {
        super.viewDidLoad()
        emailTextField.delegate = self
        passwordTextField.delegate = self
        
        // Do any additional setup after loading the view.
    }
    func textFieldShouldReturn(_ textField: UITextField) -> Bool{
        textField.resignFirstResponder()
        return true
    }
    @IBAction func registerClicked(_ sender: UIButton) {
        guard let email = emailTextField.text else {return}
        guard let password = passwordTextField.text else {return}
        guard let name = nameTextField.text else {return}
        Auth.auth().createUser(withEmail: email, password: password) {firebaseResult, error in
            if let e = error {
                let message = e.localizedDescription
                self.view.makeToast("Registration Failed: " + message, duration: 5.0, position: .top)

                print("Error")
            }
            else {

                let emailStripped = email
                    .replacingOccurrences(of: "@", with: "")
                    .replacingOccurrences(of: ".", with: "")
                guard let userID = UserDefaults.standard.object(forKey: "uniqueDeviceID") else {return}
                let userDefaultsKey = "uniqueDeviceID"
                UserDefaults.standard.set(emailStripped, forKey: userDefaultsKey)
                self.databaseRef.child("users").child(emailStripped).child("name").setValue(name)
                NotificationCenter.default.post(name: NSNotification.Name("RefreshClicked"), object: "Refresh Button Clicked!")
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
