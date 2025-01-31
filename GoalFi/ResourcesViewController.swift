//
//  ResourcesViewController.swift
//  GoalFi
//
//  Created by Vijay Venkatesan on 1/31/25.
//

import UIKit
import WebKit

class ResourcesViewController: UIViewController {
    @IBOutlet weak var webView: WKWebView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        if let url = URL(string: "https://stallingbread44.github.io/aspirefinance/"){
            let request = URLRequest(url: url)
            webView.load(request)
        }

        // Do any additional setup after loading the view.
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
