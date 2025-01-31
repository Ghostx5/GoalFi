//
//  NameTestViewController.swift
//  GoalFi
//
//  Created by Vijay Venkatesan on 1/19/25.
//

import UIKit
import FirebaseCore
import FirebaseDatabase
import Foundation

class NameTestViewController: UIViewController {
    @IBOutlet weak var mailTextField: UITextField!
    @IBOutlet weak var nameTextField: UITextField!
    @IBOutlet weak var retrievalTextField: UILabel!
    let databaseRef = Database.database().reference()
    let userDefaults = UserDefaults.standard
    let userID = UserDefaults.standard.object(forKey: "uniqueDeviceID")
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // Do any additional setup after loading the view.
    }
    @IBAction func storeButtonClicked(_ sender: UIButton) {
        let IDstring = userID
        guard let name = nameTextField.text else {return}
        databaseRef.child("users").child(IDstring as! String).child("name").setValue(name)
        NotificationCenter.default.post(name: NSNotification.Name("RefreshClicked"), object: "Refresh Button Clicked!")
        self.performSegue(withIdentifier: "nametohome", sender: self)
        
    }
    @IBAction func retrieveButtonClicked(_ sender: UIButton) {
        let IDstring = userID
        databaseRef.child("users").child(IDstring as! String).child("name").observeSingleEvent(of: .value) {snapshot in
            if let name = snapshot.value as? String{
                self.retrievalTextField.text = name
            }
            else {
                print("Name not found.")
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
}
