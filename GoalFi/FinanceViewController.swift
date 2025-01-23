//
//  FinanceViewController.swift
//  GoalFi
//
//  Created by Vijay Venkatesan on 1/22/25.
//

import UIKit
import SwiftUI
import Charts
class FinanceViewController: UIViewController {
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        let pieChartView = PieChartView()
                // Embed it in a UIHostingController
                let hostingController = UIHostingController(rootView: pieChartView)
                hostingController.view.backgroundColor = .clear

                // Add the hosting controller as a child view controller
                addChild(hostingController)
                hostingController.view.translatesAutoresizingMaskIntoConstraints = false
                view.addSubview(hostingController.view)

                // Set up constraints for the SwiftUI view
                NSLayoutConstraint.activate([
                    hostingController.view.centerXAnchor.constraint(equalTo: view.centerXAnchor),
                    hostingController.view.centerYAnchor.constraint(equalTo: view.centerYAnchor),
                    hostingController.view.widthAnchor.constraint(equalTo: view.widthAnchor, multiplier: 0.8), // 80% of the screen width
                    hostingController.view.heightAnchor.constraint(equalTo: view.heightAnchor, multiplier: 0.5) // 50% of the screen height
                ])

                hostingController.didMove(toParent: self)
        // Do any additional setup after loading the view.
        
    }
    
    struct PieChartView: View {
        struct PieData: Identifiable {
            let id = UUID()
            let category: String
            let value: Double
        }
        
        let data = [
            PieData(category: "Necessary Fees", value: 80/2),
            PieData(category: "Save For Later", value: 25),
            PieData(category: "Wants", value: 15),
            PieData(category: "Misc", value: 20)
        ]
        
        var body: some View {
            
            VStack {
                Text("Pie Chart Test YIPPEEE")
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
                .chartLegend(.visible)
                .frame(height: 300)
            }
            
            .padding()
            .background(Color.clear)
        }
    }
}
