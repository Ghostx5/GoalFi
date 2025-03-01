//
//  EditFinanceViewController.swift
//  GoalFi
//
//  Created by Vijay Venkatesan on 1/28/25.
//

import UIKit
import FirebaseDatabase


class EditFinanceViewController: UIViewController, UITextFieldDelegate {

    @IBOutlet weak var zipCodeField: UITextField!
    @IBOutlet weak var monthlySalaryField: UITextField!
    let databaseRef = Database.database().reference()
    let userDefaults = UserDefaults.standard
    let userID = UserDefaults.standard.object(forKey: "uniqueDeviceID")
    override func viewDidLoad() {
        super.viewDidLoad()
        zipCodeField.delegate = self
        monthlySalaryField.delegate = self

        // Do any additional setup after loading the view.
    }
    func textFieldShouldReturn(_ textField: UITextField) -> Bool{
        textField.resignFirstResponder()
        return true
    }
    @IBAction func storeButtonClicked(_ sender: UIButton) {
        guard let financeValue = monthlySalaryField.text else {return}
        guard let zipCode = zipCodeField.text else {return}
        let IDstring = userID
        databaseRef.child("users").child(IDstring as! String).child("Finance").child("MonthlySalary").setValue(financeValue)
        databaseRef.child("users").child(IDstring as! String).child("Finance").child("ZipCode").setValue(zipCode)
        NotificationCenter.default.post(name: NSNotification.Name("RefreshClicked"), object: "Refresh Button Clicked!")
        dismiss(animated: true, completion: nil)

        
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
