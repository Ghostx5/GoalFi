//
//  ViewController.swift
//  GoalFi
//
//  Created by Vijay Venkatesan on 1/15/25.
//

import UIKit
import FirebaseAuth
import FirebaseDatabase

class ViewController: UIViewController {
    @IBOutlet weak var welcomeLabel: UILabel!
    let databaseRef = Database.database().reference()
    let userID = UserDefaults.standard.object(forKey: "uniqueDeviceID")
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        DeviceIDManager.shared.getOrCreateUniqueDeviceID { uniqueDeviceID in
            print("Device ID: \(uniqueDeviceID)")
        }
        let IDstring = "\(userID)"
        databaseRef.child("users").child(IDstring).child("name").observeSingleEvent(of: .value) {snapshot in
            if let name = snapshot.value as? String{
                self.welcomeLabel.text = "Welcome, \(name)!"
            }
            else {
                print("Name not found.")
                
            }
            
            
            
        }
        
    }
    
    @IBAction func logOutClicked(_ sender: UIButton) {
        do {
            try Auth.auth().signOut()
            print("Signed out!")
            self.performSegue(withIdentifier: "loggedOut", sender: self)
            
            
        }
        catch let signOutError as NSError{
            print("Error signing our: \(signOutError.localizedDescription)")
        }
    }
}
