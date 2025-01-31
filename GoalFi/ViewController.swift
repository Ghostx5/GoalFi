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
        NotificationCenter.default.addObserver(self, selector: #selector(updateValues(_:)), name: NSNotification.Name("RefreshClicked"), object:nil)
        // Do any additional setup after loading the view.
        let IDstring = userID
        databaseRef.child("users").child(IDstring as! String).child("name").observeSingleEvent(of: .value) {snapshot in
            if let name = snapshot.value as? String{
                self.welcomeLabel.text = "Welcome, \(name)!"
            }
            else {
                print("Name not found.")
                
            }
            
            
            
        }
        
    }
    @objc func updateValues(_ notification: Notification){
        self.view.makeToast("Name Updated!", duration: 3.0, position: .top)
        let IDstring = userID
        databaseRef.child("users").child(IDstring as! String).child("name").observeSingleEvent(of: .value) {snapshot in
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
    @IBAction func settingsClicked(_ sender: UITapGestureRecognizer) {
            self.performSegue(withIdentifier: "settingsSegue", sender: self)
        }
        @IBAction func signOutClicked(_ sender: UITapGestureRecognizer) {
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
    
