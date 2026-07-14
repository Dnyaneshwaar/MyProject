import pytest


@pytest.fixture(scope='session')
def preSetupWork():
    print('I setup Browser instance')