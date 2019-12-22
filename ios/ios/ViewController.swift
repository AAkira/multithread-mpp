import UIKit
import common

class ViewController: UIViewController {
    override func viewDidLoad() {
        super.viewDidLoad()
        let label = UILabel(frame:
            CGRect(x: 0, y: 0, width: view.frame.size.width, height: view.frame.size.height)
        )
        label.textAlignment = .center
        label.font = label.font.withSize(26)
        self.view.addSubview(label)

        let service = GreetingService()
        service.saveGreetings(greeting: "Hello")
        service.getGreetings (
            successCallback: { result in
                label.text = result.hello
            }, errorCallback: { error in
                label.text = error.message
                print(error)
            }
        )
    }
}
