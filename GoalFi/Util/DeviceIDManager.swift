//
//  DeviceIDManager.swift
//  GoalFi
//
//  Created by Vijay Venkatesan on 1/19/25.
//

import Foundation
import FirebaseDatabase

class DeviceIDManager{
    static let shared = DeviceIDManager()
    private let databaseRef = Database.database().reference()
    private let userDefaultsKey = "uniqueDeviceID"
    func generateUDID(completion:@escaping(String) -> Void){
        let newID = Int.random(in:1_000_000...9_999_999)
        UserDefaults.standard.set(newID, forKey: userDefaultsKey)
        let newIDStr = String(newID)
        databaseRef.child("users").child(newIDStr).observeSingleEvent(of: .value) { snapshot in
            if snapshot.exists(){
                self.generateUDID(completion: completion)
            } else{
                completion(newIDStr)
            }
            
            
        }
    }
    func getOrCreateUniqueDeviceID(completion:@escaping (String) -> Void){
        if let savedID = UserDefaults.standard.string(forKey: userDefaultsKey){
            completion(savedID)
            return
            
        }
        else{
            self.generateUDID(completion: completion)
        }
    }
}
