//
//  FinanceViewController.swift
//  GoalFi
//
//  Created by Vijay Venkatesan on 1/22/25.
//


import UIKit
import SwiftUI
import Charts
import FirebaseDatabase
func updateValues(_ notification: Notification){
    
}

class FinanceViewController: UIViewController {
    let databaseRef = Database.database().reference()
    @IBOutlet weak var summaryLabel: UILabel!
    @IBOutlet weak var SavingsTextLabel: UILabel!
    @IBOutlet weak var WantsTextLabels: UILabel!
    @IBOutlet weak var NeedsTextLabel: UILabel!
    let userDefaults = UserDefaults.standard
    let userID = UserDefaults.standard.object(forKey: "uniqueDeviceID")
    override func viewDidLoad() {
        super.viewDidLoad()

       let IDstring = userID
        databaseRef.child("users").child(IDstring as! String).child("Finance").child("Monthly Salary").observeSingleEvent(of: .value) {snapshot,placehold in if let money = snapshot.value as? String{
            self.summaryLabel.text = "You make: $" + money
            let funny = Double(money)
            let needs = String((funny ?? 1000) * 0.50)
            let savings = String((funny ?? 1000) * 0.30)
            let wants = String((funny ?? 1000) * 0.20)
            self.NeedsTextLabel.text = "$"+needs+" on needs."
            self.WantsTextLabels.text = "$"+wants+" on wants."
            self.SavingsTextLabel.text = "$"+savings+" to your savings."
        }
            else{
                print("Money not found")
            }
        }
            let pieChartView = PieChartView()
            // Embed it in a UIHostingController
            let hostingController = UIHostingController(rootView: pieChartView)
            hostingController.view.backgroundColor = .clear
            
            // Add the hosting controller as a child view controller
            self.addChild(hostingController)
            hostingController.view.translatesAutoresizingMaskIntoConstraints = false
            self.view.addSubview(hostingController.view)
            
            // Set up constraints for the SwiftUI view
            NSLayoutConstraint.activate([
                hostingController.view.leadingAnchor.constraint(equalTo: view.leadingAnchor, constant: 40),
                hostingController.view.topAnchor.constraint(equalTo: view.topAnchor, constant: 100),
                hostingController.view.widthAnchor.constraint(equalTo: view.widthAnchor, multiplier: 0.8), // 80% of the screen width
                hostingController.view.heightAnchor.constraint(equalTo: view.heightAnchor, multiplier: 0.5) // 50% of the screen height
            ])
            
            hostingController.didMove(toParent: self)
        }
        // Do any additional setup after loading the view.
        
    }
    
    struct PieChartView: View {
        struct PieData: Identifiable {
            let id = UUID()
            let category: String
            let value: Double
        }
        
        let data = [
            PieData(category: "Needs", value: 50),
            PieData(category: "Savings", value: 30),
            PieData(category: "Wants", value: 20)
        ]
        
        var body: some View {
            
            VStack {
                Text("The 50-30-20 Plan")
                    .font(.headline)
                
                Chart {
                    ForEach(data) { segment in
                        SectorMark(
                            angle: .value("Value", segment.value),
                            innerRadius: .ratio(0.5), // Adjust for donut style
                            outerRadius: .ratio(1.0)
                        )
                        .foregroundStyle(by: .value("Category", segment.category))
                    }
                }
                .frame(height: 300)
                .chartLegend(.hidden)
            }
            
            .padding()
            .background(Color.clear)
        }
    }

