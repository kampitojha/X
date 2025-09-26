/*
Jetpack Compose: Complete Guide with Explanations

Jetpack Compose is Android’s modern toolkit for building native UI. It simplifies and accelerates UI development on Android with less code, powerful tools, and intuitive Kotlin APIs.

Let's break down all the important topics of Jetpack Compose, and explain what each thing does:

1. **Composable Functions**
   - **Kya hai?**: Functions marked with `@Composable` that describe your UI.
   - **Kya karti hai?**: Ye functions UI ko define karte hain. Jaise ki XML me layout likhte the, ab Compose me function likhte hain.
   - **Example:**
     ```
     @Composable
     fun Greeting(name: String) {
         Text(text = "Hello $name!")
     }
     ```

2. **@Composable Annotation**
   - **Kya hai?**: Ye ek annotation hai jo batata hai ki function Compose UI bana raha hai.
   - **Kya karti hai?**: Compiler ko batata hai ki is function ko Compose system handle karega.

3. **Recomposition**
   - **Kya hai?**: Jab data change hota hai, Compose automatically UI ko update karta hai.
   - **Kya karti hai?**: Sirf wahi part UI ka dobara draw hota hai jisme data change hua hai.

4. **State Management**
   - **Kya hai?**: UI me data ko manage karne ka tarika.
   - **Kya karti hai?**: `remember`, `mutableStateOf`, `State`, etc. ka use hota hai.
   - **Example:**
     ```
     var count by remember { mutableStateOf(0) }
     Button(onClick = { count++ }) {
         Text("Clicked $count times")
     }
     ```

5. **Layouts**
   - **Kya hai?**: UI ko arrange karne wale containers.
   - **Kya karti hai?**: Jaise `Column`, `Row`, `Box`, etc. Ye children ko arrange karte hain.
   - **Example:**
     ```
     Column {
         Text("Line 1")
         Text("Line 2")
     }
     ```

6. **Modifiers**
   - **Kya hai?**: UI elements ko style, layout, gesture, etc. dene ke liye use hote hain.
   - **Kya karti hai?**: Size, padding, background, click, etc. set karte hain.
   - **Example:**
     ```
     Text(
         "Hello",
         modifier = Modifier.padding(16.dp).background(Color.Yellow)
     )
     ```

7. **Material Design Components**
   - **Kya hai?**: Pre-built UI components jaise Button, Card, TextField, etc.
   - **Kya karti hai?**: Material Design ke rules follow karte hain, ready-made UI dete hain.
   - **Example:**
     ```
     Button(onClick = { /*...*/ }) {
         Text("Click me")
     }
     ```

8. **Theming**
   - **Kya hai?**: App ke colors, typography, shapes define karna.
   - **Kya karti hai?**: App ka consistent look and feel maintain karti hai.
   - **Example:**
     ```
     MaterialTheme(
         colors = lightColors(primary = Color.Blue),
         typography = Typography(),
         shapes = Shapes()
     ) {
         // App content
     }
     ```

9. **Lists (LazyColumn, LazyRow)**
   - **Kya hai?**: Scrollable lists banane ke liye.
   - **Kya karti hai?**: Efficiently large data ko scrollable list me dikhata hai.
   - **Example:**
     ```
     LazyColumn {
         items(100) { index ->
             Text("Item #$index")
         }
     }
     ```

10. **Navigation**
    - **Kya hai?**: App ke screens ke beech navigation.
    - **Kya karti hai?**: Compose Navigation library se screens switch karte hain.
    - **Example:**
      ```
      NavHost(navController, startDestination = "home") {
          composable("home") { HomeScreen() }
          composable("details") { DetailsScreen() }
      }
      ```

11. **Side Effects**
    - **Kya hai?**: UI ke bahar ka kaam, jaise API call, database, etc.
    - **Kya karti hai?**: `LaunchedEffect`, `SideEffect`, etc. se handle karte hain.
    - **Example:**
      ```
      LaunchedEffect(Unit) {
          // Do something when composable enters composition
      }
      ```

12. **Custom Views & Drawing**
    - **Kya hai?**: Apna custom UI ya graphics banana.
    - **Kya karti hai?**: `Canvas`, `draw*` functions se custom drawing kar sakte hain.
    - **Example:**
      ```
      Canvas(modifier = Modifier.size(100.dp)) {
          drawCircle(Color.Red)
      }
      ```

13. **Animation**
    - **Kya hai?**: UI me movement ya transition lana.
    - **Kya karti hai?**: `animate*AsState`, `updateTransition`, etc. se animation add karte hain.
    - **Example:**
      ```
      val alpha by animateFloatAsState(targetValue = if (visible) 1f else 0f)
      ```

14. **Interoperability with XML**
    - **Kya hai?**: Purane XML layouts ke sath kaam karna.
    - **Kya karti hai?**: `AndroidView` se XML views ko Compose me use kar sakte hain.

15. **Preview**
    - **Kya hai?**: Android Studio me UI ka live preview dekhna.
    - **Kya karti hai?**: `@Preview` annotation se composable ka preview dikhata hai.
    - **Example:**
      ```
      @Preview
      @Composable
      fun PreviewGreeting() {
          Greeting("Android")
      }
      ```

16. **Testing**
    - **Kya hai?**: Compose UI ka test likhna.
    - **Kya karti hai?**: Compose Testing APIs se UI ko test kar sakte hain.

**Summary Table:**

| Topic                | Kya hai? (What is it?)         | Kya karti hai? (What does it do?)         |
|----------------------|-------------------------------|-------------------------------------------|
| Composable           | UI function                   | UI define karta hai                      |
| Modifier             | Styling/layout helper          | Size, color, click, etc. set karta hai   |
| State                | Data holder                   | UI ko update karta hai                   |
| Layouts              | Arrange children              | UI ko arrange karta hai                  |
| Material Components  | Ready-made widgets            | Material Design follow karta hai         |
| Navigation           | Screen switching              | App me navigation handle karta hai       |
| Animation            | Motion/transition             | UI me animation lata hai                 |
| Side Effects         | External work                 | API call, etc. handle karta hai          |
| Preview              | Live UI preview               | Android Studio me preview dikhata hai    |

**Conclusion:**
Jetpack Compose se UI banana easy, fast, aur modern ho gaya hai. Har cheez function ke through hoti hai, aur state management se UI automatically update hota hai. Modifiers se styling aur gesture add kar sakte hain. Material components se ready-made UI milta hai. Navigation, animation, aur testing bhi Compose me simple hai.

Aapko Compose seekhne ke liye sabse pehle Composable functions, state, layouts, modifiers, aur Material components pe focus karna chahiye. Baaki topics jaise navigation, animation, side effects, etc. aap project me use karte hue seekh sakte hain.

Happy Composing! 🚀
*/
// Jetpack Compose ke aur bhi important concepts aur features:

17. **Theming & Styling**
    - **Kya hai?**: App ka look & feel set karna (colors, typography, shapes).
    - **Kya karti hai?**: `MaterialTheme`, custom themes, dark/light mode support.
    - **Example:**
      ```
      MaterialTheme(
          colors = darkColors(),
          typography = Typography(),
          shapes = Shapes()
      ) {
          // App content
      }
      ```

18. **Custom Composables**
    - **Kya hai?**: Apne khud ke UI components banana.
    - **Kya karti hai?**: Reusable UI blocks create kar sakte hain.
    - **Example:**
      ```
      @Composable
      fun MyButton(text: String, onClick: () -> Unit) {
          Button(onClick = onClick) {
              Text(text)
          }
      }
      ```

19. **Recomposition**
    - **Kya hai?**: Jab state change hoti hai to UI ka dobara draw hona.
    - **Kya karti hai?**: Compose automatically UI ko update karta hai jab data/state change hoti hai.

20. **Remember & rememberSaveable**
    - **Kya hai?**: State ko composable ke andar ya across configuration changes (jaise screen rotate) me preserve karna.
    - **Kya karti hai?**: `remember` se local state, `rememberSaveable` se state survive hoti hai.
    - **Example:**
      ```
      var count by rememberSaveable { mutableStateOf(0) }
      ```

21. **LaunchedEffect, SideEffect, DisposableEffect**
    - **Kya hai?**: Side effects ko handle karna (jaise API call, logging, etc.)
    - **Kya karti hai?**: Compose lifecycle ke sath external kaam karne me help karta hai.
    - **Example:**
      ```
      LaunchedEffect(Unit) {
          // Coroutine launch hota hai
      }
      ```

22. **DerivedStateOf & SnapshotFlow**
    - **Kya hai?**: State se derived/computed values banana, ya state changes ko observe karna.
    - **Kya karti hai?**: Efficient recomposition aur state observation.

23. **Lists & Lazy Components**
    - **Kya hai?**: Large data lists ko efficiently dikhana.
    - **Kya karti hai?**: `LazyColumn`, `LazyRow`, `LazyGrid` se scrolling lists banate hain.
    - **Example:**
      ```
      LazyColumn {
          items(list) { item ->
              Text(item)
          }
      }
      ```

24. **Gestures & Touch Handling**
    - **Kya hai?**: Tap, drag, swipe, etc. handle karna.
    - **Kya karti hai?**: `Modifier.clickable`, `pointerInput`, etc. se gestures detect karte hain.

25. **Accessibility**
    - **Kya hai?**: App ko sabhi users ke liye usable banana (screen readers, etc.)
    - **Kya karti hai?**: `semantics`, `contentDescription`, etc. se accessibility improve hoti hai.

26. **Interop with ViewModel & LiveData/Flow**
    - **Kya hai?**: ViewModel, LiveData, Flow ko Compose ke sath use karna.
    - **Kya karti hai?**: `collectAsState()`, `observeAsState()` se reactive data UI me la sakte hain.

27. **ConstraintLayout in Compose**
    - **Kya hai?**: Complex layouts banana.
    - **Kya karti hai?**: `ConstraintLayout` composable se advanced UI layouts bana sakte hain.

28. **Dialogs, Snackbars, BottomSheets**
    - **Kya hai?**: Temporary UI overlays dikhana.
    - **Kya karti hai?**: `AlertDialog`, `Snackbar`, `ModalBottomSheetLayout` se overlays bana sakte hain.

29. **Image Loading**
    - **Kya hai?**: Network/local images dikhana.
    - **Kya karti hai?**: `Image`, `rememberImagePainter` (Accompanist/Coil) se images load karte hain.

30. **Performance Optimization**
    - **Kya hai?**: App ko fast aur smooth banana.
    - **Kya karti hai?**: `key` in lists, avoiding unnecessary recomposition, using `derivedStateOf`, etc.

31. **Custom Drawing & Graphics**
    - **Kya hai?**: Apne custom shapes, graphics, ya charts banana.
    - **Kya karti hai?**: `Canvas`, `draw*` modifiers se custom drawing possible hai.

32. **Animations: Advanced**
    - **Kya hai?**: Complex animations (physics, transitions, animated visibility).
    - **Kya karti hai?**: `AnimatedVisibility`, `updateTransition`, `Animatable`, etc.

33. **Multiple Screens & Navigation**
    - **Kya hai?**: App me alag-alag screens (pages) banana aur unke beech navigation.
    - **Kya karti hai?**: `NavHost`, `NavController` se navigation handle hota hai.

34. **Dependency Injection**
    - **Kya hai?**: Dependencies ko manage karna (jaise ViewModel, Repository).
    - **Kya karti hai?**: Hilt, Koin, etc. Compose ke sath use ho sakte hain.

35. **Testing: UI & Unit**
    - **Kya hai?**: Apne composables ka test likhna.
    - **Kya karti hai?**: `composeTestRule`, `assertIsDisplayed`, etc. se UI test likh sakte hain.

36. **Migration from XML to Compose**
    - **Kya hai?**: Purane XML layouts ko Compose me convert karna.
    - **Kya karti hai?**: Step-by-step migration, hybrid approach (XML + Compose).

37. **Best Practices**
    - **Kya hai?**: Code ko maintainable, scalable, aur efficient banana.
    - **Kya karti hai?**: Small composables, unidirectional data flow, state hoisting, etc.

**Ek Line Me:**
Jetpack Compose me UI banana declarative, modular, aur modern hai. Aap har cheez function ke through likhte ho, state-driven UI hota hai, aur customization, animation, testing, performance optimization, accessibility, aur integration sab kuch Compose me possible hai.

**Aapko kya seekhna chahiye?**
- Composable functions, state management, layouts, modifiers, MaterialTheme
- Lists (LazyColumn), navigation, animation, side effects
- ViewModel, LiveData/Flow integration, testing, accessibility
- Custom composables, performance tips, theming, gestures

**Resources:**
- Official docs: https://developer.android.com/jetpack/compose
- Codelabs: https://developer.android.com/codelabs/jetpack-compose-basics
- Sample apps: https://github.com/android/compose-samples

Compose seekhne ka best tarika hai: chhota project banao, har feature ko use karo, aur documentation padhte raho!

Happy Learning! ✨
